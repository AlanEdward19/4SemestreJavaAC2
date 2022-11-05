public class ItemPedido
{
    private int _quantidade;
    private String _nomeProduto;
    private double _precoUnitario;
    private double _valorTotal;

    public ItemPedido(int quantidade, String nomeProduto, double precoUnitario, double valorTotal)
    {
        _quantidade = quantidade;
        _nomeProduto = nomeProduto;
        _precoUnitario = precoUnitario;
        _valorTotal = valorTotal;
    }

    public int get_quantidade() {
        return _quantidade;
    }

    public void set_quantidade(int _quantidade) {
        this._quantidade = _quantidade;
    }

    public String get_nomeProduto() {
        return _nomeProduto;
    }

    public void set_nomeProduto(String _nomeProduto) {
        this._nomeProduto = _nomeProduto;
    }

    public double get_precoUnitario() {
        return _precoUnitario;
    }

    public void set_precoUnitario(double _precoUnitario) {
        this._precoUnitario = _precoUnitario;
    }

    public double get_valorTotal() {
        return _valorTotal;
    }

    public void set_valorTotal(double _valorTotal) {
        this._valorTotal = _valorTotal;
    }

    public void Print()
    {
        System.out.println("-- " + get_nomeProduto() + "--");
        System.out.println("Quantidade: " + get_quantidade());
        System.out.println("Preço Unitario: " + get_precoUnitario());
        System.out.println("Valor Total: " + get_valorTotal());
        System.out.println("-- --\n");
    }
}
