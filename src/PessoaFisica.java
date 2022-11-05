public class PessoaFisica extends Cliente {
    private String _cpf;

    public PessoaFisica(String nome, String email, String cpf)
    {
        super (nome, email);
        _cpf = cpf;
    }

    public String get_cpf() {
        return _cpf;
    }

    public void set_cpf(String _cpf) {
        this._cpf = _cpf;
    }

    @Override
    public void Print() {
        super.Print();
        System.out.println("CPF: " + get_cpf());
        System.out.println("-- --\n");
    }
}
