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
                CriarCliente();
                break;

            case 6:
                Relatorios(clientesCadastrados, produtosCadastrados, pedidosCadastrados, fornecedoresCadastrados);
                break;

            case 7:
                System.exit(0);
                break;
        }
    }

    public static void Menu() {
        System.out.println("Menu Principal");
        System.out.println("[1] Cadastrar Cliente"); // feito
        System.out.println("[2] Cadastrar Fornecedor"); //feito
        System.out.println("[3] Cadastrar Produto"); // feito
        System.out.println("[4] Efetuar Pedido"); // falta terminar
        System.out.println("[5] Baixa de pagamento de um pedido");
        System.out.println("[6] Relatorios"); // falta terminar
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
        System.out.println("[5] Listar todos os pedidos em intervalo de datas");
        System.out.println("[6] Buscar pedido por codigo");
        System.out.println("[7] Listar todos os pedidos pagos");
        System.out.println("[8] Buscar produto por nome");
        System.out.println("[9] Calculo total de pedidos abertos");
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
                CriarCliente();
                break;

            case 7:
                System.exit(0);
                break;

            case 8:
                System.exit(0);
                break;

            case 9:
                System.exit(0);
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

        if (reader.nextLine().contains("S"))
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

        System.out.println("Qual o email do fornecedor?");
        email = reader.nextLine();

        System.out.println("Qual o CNPJ do fornecedor?");
        cnpj = reader.nextLine();

        fornecedor = new Fornecedor(nome, email, cnpj, pj);

        return fornecedor;
    }

    public static Pedido CriarPedido(List<ItemPedido> produtosDisponiveis, ArrayList<Cliente> clientesCadastrados) throws Exception {
        List<ItemPedido> produtoEscolhido = new ArrayList<ItemPedido>();
        Cliente cliente;
        Pedido pedido;
        int resposta = 0;

        String nomeProduto, cnpjCpfCliente;
        int identificador;
        double valorTotalPedido = 0;
        Date data;
        boolean pago, clienteExiste, produtoExiste;

        var readerString = new Scanner(System.in);
        var readerInt = new Scanner(System.in);
        var readerDouble = new Scanner(System.in);

        System.out.println("Qual o ID deste pedido?");
        identificador = readerInt.nextInt();

        System.out.println("Qual a data deste pedido? (dd-MM-yyyy)");
        data = new SimpleDateFormat("dd-MM-yyyy").parse(readerString.next());

        do {
            System.out.println("Qual o CNPJ ou CPF do cliente?");
            cnpjCpfCliente = readerString.nextLine();

            clienteExiste = VerificarSeClienteExiste(clientesCadastrados, cnpjCpfCliente);

            if (!clienteExiste) {
                System.out.println("Cliente Não existe, insira novamente CNPJ/CPF");
            }

        } while (!clienteExiste);

        System.out.println("Este pedido ja foi pago?");
        if (readerString.nextLine().contains("S"))
            pago = true;

        else
            pago = false;

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
                if (produto.get_nomeProduto().equals(nomeProduto)) {
                    produtoEscolhido.add(produto);
                }
            }

            System.out.println("\nDeseja adicionar outro produto?\n[1]SIM\n[2]NÃO\n");
            resposta = readerInt.nextInt();

        } while (resposta != 0);

        for (var produto : produtoEscolhido) {
            valorTotalPedido += produto.get_valorTotal();
        }

        pedido = new Pedido(produtoEscolhido, identificador, data, valorTotalPedido, cnpjCpfCliente, pago);

        return pedido;

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

                if ((dataInicial.equals(dataItem) || dataInicial.after(dataItem)) 
                    && (dataFinal.before(dataItem) || dataFinal.equals(dataItem))) {
                    
                    ((Pedido) item).Print();
                }
            }
        }
    }//necessario testar a logica e tentar melhorar o codigo
}