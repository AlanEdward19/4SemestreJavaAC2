import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        int opcao;
        var reader = new Scanner(System.in);
        var clientesCadastrados = new ArrayList<Cliente>();
        var fornecedoresCadastrados = new ArrayList<Fornecedor>();
        var produtosCadastrados = new ArrayList<ItemPedido>();
        var pedidosCadastrados = new ArrayList<Pedido>();

        do {
            Menu();
            opcao = reader.nextInt();

            switch (opcao) {
                case 1:
                    clientesCadastrados.add(CriarCliente());
                    break;
    
                case 2:
                    fornecedoresCadastrados.add(CriarFornecedor()); 
                    break;
    
                case 3:
                    produtosCadastrados.add(CriarProduto());
                    break;
    
                case 4:
    
                    pedidosCadastrados.add(CriarPedido(produtosCadastrados, clientesCadastrados));
                    break;
    
                case 5:
                    DarBaixaPagamentoPedido(pedidosCadastrados);
                    break;
    
                case 6:
                    Relatorios(clientesCadastrados, produtosCadastrados, pedidosCadastrados, fornecedoresCadastrados);
                    break;
    
                case 7:
                    System.exit(0);
                    break;
            }
        } while (opcao != 7);
        
    }

    public static void Menu() {
        System.out.println("Menu Principal");
        System.out.println("[1] Cadastrar Cliente"); // feito
        System.out.println("[2] Cadastrar Fornecedor"); //feito
        System.out.println("[3] Cadastrar Produto"); // feito
        System.out.println("[4] Efetuar Pedido"); // Feito
        System.out.println("[5] Baixa de pagamento de um pedido"); // Feito
        System.out.println("[6] Relatorios"); // falta terminar (terminado) ??
        System.out.println("[7] Sair"); // feito
    }

    public static void Relatorios(ArrayList<Cliente> clientesCadastrados, ArrayList<ItemPedido> produtosCadastrados,
            ArrayList<Pedido> pedidosCadastrados, ArrayList<Fornecedor> fornecedoresCadastrados) throws Exception {
        int opcao;
        var reader = new Scanner(System.in);

        System.out.println("[1] Listar todos os clientes"); // feito
        System.out.println("[2] Listar todos os fornecedores"); //feito
        System.out.println("[3] Listar todos os produtos"); // feito
        System.out.println("[4] Listar todos os pedidos"); // feito
        System.out.println("[5] Listar todos os pedidos em intervalo de datas"); // feito??
        System.out.println("[6] Buscar pedido por codigo"); // feito
        System.out.println("[7] Listar todos os pedidos pagos"); // Feito
        System.out.println("[8] Buscar produto por nome"); // Feito
        System.out.println("[9] Calculo total de pedidos abertos"); // Feito
        System.out.println("[10] Voltar");

        opcao = reader.nextInt();

        switch (opcao) {
            case 1:
                Listar(clientesCadastrados);
                break;

            case 2:
                Listar(fornecedoresCadastrados);
                break;

            case 3:
                Listar(produtosCadastrados);
                break;

            case 4:
                Listar(pedidosCadastrados);
                break;

            case 5:
                ListarPedidosPorDatas(pedidosCadastrados);
                break;

            case 6:
                BuscaPedidosId(pedidosCadastrados);
                break;

            case 7:
                BuscaPedidosPagos(pedidosCadastrados);
                break;

            case 8:
                BuscaProdutoNome(produtosCadastrados);
                break;

            case 9:
                CalculaPedidosAbertos(pedidosCadastrados);
                break;

            case 10:
                break;
        }

    }

    public static Cliente CriarCliente() {
        Cliente cliente;

        var reader = new Scanner(System.in);

        String nome, email, cpfCnpj;
        boolean pj;

        System.out.println("Qual o nome do Cliente?");
        nome = reader.nextLine();

        System.out.println("Qual o email do Cliente?");
        email = reader.nextLine();

        System.out.println("Cliente é pessoa Juridica?");

        if (reader.nextLine().toUpperCase().contains("S"))
            pj = true;

        else
            pj = false;

        if (pj) {
            System.out.println("Qual o CNPJ do cliente?");
            cpfCnpj = reader.nextLine();

            cliente = new PessoaJuridica(nome, email, cpfCnpj);
        } else {
            System.out.println("Qual o CPF do cliente?");
            cpfCnpj = reader.nextLine();

            cliente = new PessoaFisica(nome, email, cpfCnpj);
        }

        return cliente;
    }

    public static ItemPedido CriarProduto() {
        ItemPedido produto;

        var readerString = new Scanner(System.in);
        var readerInt = new Scanner(System.in);
        var readerDouble = new Scanner(System.in);

        int quantidade;
        double precoUnitario, valorTotal;
        String nomeProduto;

        System.out.println("Qual o nome do Produto?");
        nomeProduto = readerString.nextLine();

        System.out.println("Qual o valor de " + nomeProduto + "?");
        precoUnitario = readerDouble.nextDouble();

        System.out.println("Qual o valor total de " + nomeProduto + "?");
        valorTotal = readerDouble.nextDouble();

        System.out.println("Quantas unidades?");
        quantidade = readerInt.nextInt();

        produto = new ItemPedido(quantidade, nomeProduto, precoUnitario, valorTotal);

        return produto;
    }

    public static Fornecedor CriarFornecedor() {

        Fornecedor fornecedor;

        var reader = new Scanner(System.in);

        String nome, email, cnpj;
        boolean pj = true;

        System.out.println("Qual o nome do fornecedor?");
        nome = reader.nextLine();

        System.out.println("Qual o CNPJ do fornecedor?");
        cnpj = reader.nextLine();

        fornecedor = new Fornecedor(nome, cnpj, pj);

        return fornecedor;
    }

    public static boolean VerificarSeClienteExiste(ArrayList<Cliente> clientesCadastrados, String cnpjCpf) {
        boolean clienteExiste = false;

        for (var cliente : clientesCadastrados) {
            if (cliente instanceof PessoaJuridica) {
                if (((PessoaJuridica) cliente).get_cnpj().equals(cnpjCpf))
                    clienteExiste = true;
            }

            else if (cliente instanceof PessoaFisica) {
                if (((PessoaFisica) cliente).get_cpf().equals(cnpjCpf))
                    clienteExiste = true;
            }
        }

        return clienteExiste;
    }

    public static boolean VerificarSeProdutoExiste(List<ItemPedido> produtosCadastrados, String nomeProduto) {
        boolean produtoExiste = false;

        for (var produto : produtosCadastrados) {
            if (produto.get_nomeProduto().equals(nomeProduto)) {
                produtoExiste = true;
            }
        }

        return produtoExiste;
    }

    public static boolean VerificarSeFornecedorExiste(ArrayList<Fornecedor> fornecedoresCadastrados, String cnpj) {
        boolean fornecedorExiste = false;

        for (var fornecedor : fornecedoresCadastrados) {
            if (fornecedor.get_cnpjFornecedor().equals(cnpj)) {
                fornecedorExiste = true;
            }
        }

        return fornecedorExiste;
    }

    public static <T> void Listar(ArrayList<T> cadastrados) {
        for (var item : cadastrados) {
            if (item instanceof Cliente) {
                ((Cliente) item).Print();
            }

            else if (item instanceof ItemPedido) {
                ((ItemPedido) item).Print();
            }

            else if (item instanceof Pedido) {
                ((Pedido) item).Print();
            }

            else if (item instanceof Fornecedor) {
                ((Fornecedor) item).Print();
            }
        }
    }

    public static <T> void ListarPedidosPorDatas(ArrayList<T> cadastrados) throws Exception {
        Date dataInicial;
        Date dataFinal; 
        var readerString = new Scanner(System.in);
        
        System.out.println("Digite a data inicial (dd-MM-yyyy): ");
        dataInicial = new SimpleDateFormat("dd-MM-yyyy").parse(readerString.next());
        

        System.out.println("Digite a data final (dd-MM-yyyy): ");
        dataFinal = new SimpleDateFormat("dd-MM-yyyy").parse(readerString.next());

        for (var item : cadastrados) {

            if (item instanceof Pedido ) {

                var dataItem = ((Pedido) item).get_data();

                if ((dataInicial.equals(dataItem) || dataInicial.before(dataItem)) 
                    && (dataFinal.after(dataItem) || dataFinal.equals(dataItem))) {
                    
                    ((Pedido) item).Print();
                }
            }
        }
    }//necessario testar a logica e tentar melhorar o codigo

    public static Pedido CriarPedido(List<ItemPedido> produtosDisponiveis, ArrayList<Cliente> clientesCadastrados) throws Exception {
        var produtoEscolhido = new ArrayList<ItemPedido>();
        Cliente cliente;
        Pedido pedido;
        int resposta = 0;

        String nomeProduto, cnpjCpfCliente;
        int identificador;
        double valorTotalPedido = 0;
        Date data;
        boolean pago = false, clienteExiste, produtoExiste;

        var readerStringDate = new Scanner(System.in);
        var readerString = new Scanner(System.in);
        var readerInt = new Scanner(System.in);
        var readerDouble = new Scanner(System.in);

        System.out.println("Qual o ID deste pedido?");
        identificador = readerInt.nextInt();

        System.out.println("Qual a data deste pedido? (dd-MM-yyyy)");
        data = new SimpleDateFormat("dd-MM-yyyy").parse(readerStringDate.next());

        do {
            System.out.println("Qual o CNPJ ou CPF do cliente?");
            cnpjCpfCliente = readerString.nextLine();

            clienteExiste = VerificarSeClienteExiste(clientesCadastrados, cnpjCpfCliente);

            if (!clienteExiste) {
                System.out.println("Cliente Não existe, insira novamente CNPJ/CPF");
            }

        } while (!clienteExiste);

        do {
            do {
                System.out.println("Qual produto está neste pedido?");
                nomeProduto = readerString.nextLine();

                produtoExiste = VerificarSeProdutoExiste(produtosDisponiveis, nomeProduto);

                if (!produtoExiste) {
                    System.out.println("Produto Não existe, insira nome do produto");
                }
            } while (!produtoExiste);

            for (var produto : produtosDisponiveis) // Varre produtos cadastrados, e procura compatibilidade
            {
                if (produto.get_nomeProduto().toLowerCase().equals(nomeProduto.toLowerCase())) {
                    produtoEscolhido.add(produto);
                }
            }

            System.out.println("\nDeseja adicionar outro produto no pedido?\n[1]SIM\n[2]NÃO\n");
            resposta = readerInt.nextInt();

        } while (resposta != 2);

        for (var produto : produtoEscolhido) {
            valorTotalPedido += produto.get_valorTotal();
        }

        pedido = new Pedido(produtoEscolhido, identificador, data, valorTotalPedido, cnpjCpfCliente, pago);

        return pedido;

    }

    public static void DarBaixaPagamentoPedido(ArrayList<Pedido> pedido) {
        var reader = new Scanner(System.in);
        int idPedido;

        System.out.println("Digite o identificador do pedido que pretende-se dar baixa: ");
        idPedido = reader.nextInt();

        for (int i = 0; i < pedido.size(); i++) {
            if (pedido.get(i).get_idetificador() == idPedido) {
                pedido.get(i).set_pago(true);
            }
        }
    }

    public static void BuscaPedidosId(ArrayList<Pedido> pedido) {
        var reader = new Scanner(System.in);
        int idPedido;

        System.out.println("Digite o identificador do pedido que pretende-se buscar: ");
        idPedido = reader.nextInt();

        for (int i = 0; i < pedido.size(); i++) {
            if (pedido.get(i).get_idetificador() == idPedido) {
                pedido.get(i).Print();
            }
        }
    }

    public static void BuscaPedidosPagos(ArrayList<Pedido> pedido) {
        var reader = new Scanner(System.in);
     
        for (int i = 0; i < pedido.size(); i++) {
            if (pedido.get(i).is_pago()) {
                pedido.get(i).Print();
            }
        }
    }

    public static void BuscaProdutoNome(ArrayList<ItemPedido> produto) {
        var reader = new Scanner(System.in);
        var nome = "";

        System.out.println("Digite o nome do produto que pretende-se buscar: ");
        nome = reader.nextLine();        
     
        for (int i = 0; i < produto.size(); i++) {
            if (produto.get(i).get_nomeProduto().equals(nome)) {
                produto.get(i).Print();
            }
        }
    }

    public static void CalculaPedidosAbertos(ArrayList<Pedido> pedido) {
        int count = 0;

        for (int i = 0; i < pedido.size(); i++) {
            if (!(pedido.get(i).is_pago())) {
                count++;
            }
        }

        System.out.println("Pedidos totais em aberto (não pagos): " + count);
    }
}