public class Fornecedor {
    private String _nome;
    private String _cnpjFornecedor;
    private boolean _pj;
   
    

    public Fornecedor(String nome, String cnpjFornecedor, boolean pj) {
        _nome = nome;
        _cnpjFornecedor = cnpjFornecedor;
        _pj = pj;
    }

    public String get_nome() {
        return _nome;
    }

    public void set_nome(String _nome) {
        this._nome = _nome;
    }

    public String get_cnpjFornecedor() {
        return _cnpjFornecedor;
    }

    public void set_cnpjFornecedor(String _cnpjFornecedor) {
        this._cnpjFornecedor = _cnpjFornecedor;
    }

    public boolean is_pj() {
        return _pj;
    }

    public void set_pj(boolean _pj) {
        this._pj = _pj;
    }

    public void Print() {
        System.out.println("-- " + get_nome() + " --");
        System.out.println("CNPJ: " + get_cnpjFornecedor());
    }

    
}
