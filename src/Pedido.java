import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Pedido {
    private List<ItemPedido> _itemPedido;
    private int _idetificador;
    private Date _data;
    private double _valorTotalPedido;
    private String _cnpjCpfCliente;
    private boolean _pago;

    public Pedido (List<ItemPedido> itemPedido, int identificador, Date data, double valorTotalPedido, String cnpjCpfCliente, boolean pago)
    {
        _itemPedido = itemPedido;
        _idetificador = identificador;
        _data = data;
        _valorTotalPedido = valorTotalPedido;
        _cnpjCpfCliente = cnpjCpfCliente;
        _pago = pago;
    }

    public List<ItemPedido> get_itemPedido() {
        return _itemPedido;
    }

    public void set_itemPedido(List<ItemPedido> _itemPedido) {
        this._itemPedido = _itemPedido;
    }

    public int get_idetificador() {
        return _idetificador;
    }

    public void set_idetificador(int _idetificador) {
        this._idetificador = _idetificador;
    }

    public Date get_data() {
        return _data;
    }

    public void set_data(Date _data) {
        this._data = _data;
    }

    public double get_valorTotalPedido() {
        return _valorTotalPedido;
    }

    public void set_valorTotalPedido(double _valorTotalPedido) {
        this._valorTotalPedido = _valorTotalPedido;
    }

    public String get_cnpjCpfCliente() {
        return _cnpjCpfCliente;
    }

    public void set_cnpjCpfCliente(String _cnpjCpfCliente) {
        this._cnpjCpfCliente = _cnpjCpfCliente;
    }

    public boolean is_pago() {
        return _pago;
    }

    public void set_pago(boolean _pago) {
        this._pago = _pago;
    }

    public void Print()
    {
        String pagou;

        if (is_pago())
            pagou = "Sim" ;
        else
            pagou = "Não";

        System.out.println("-- Pedido: " + get_idetificador() + "--");
        //ver a necessidade de printar os itens do pedido
        System.out.println("Data: " + get_data());
        System.out.println("Valor Total Pedido: " + get_valorTotalPedido());
        System.out.println("CNPJ/CPF cliente: " + get_cnpjCpfCliente());
        System.out.println("Pago: " + pagou);
    }
}
