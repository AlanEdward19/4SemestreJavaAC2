public class PessoaJuridica extends Cliente
{
    private String _cnpj;

    public PessoaJuridica (String nome, String email, String cnpj)
    {
        super (nome, email);
        _cnpj = cnpj;
    }

    public String get_cnpj() {
        return _cnpj;
    }

    public void set_cnpj(String _cnpj) {
        this._cnpj = _cnpj;
    }

    @Override
    public void Print() {
        super.Print();
        System.out.println("CNPJ: " + get_cnpj());
        System.out.println("-- --\n");
    }
}
