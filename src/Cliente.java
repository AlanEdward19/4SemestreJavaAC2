public class Cliente {
    private String _nome;
    private String _email;

    public Cliente (String nome, String email)
    {
        _nome = nome;
        _email = email;
    }

    public String get_nome() {
        return _nome;
    }

    public void set_nome(String _nome) {
        this._nome = _nome;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public void Print()
    {
        System.out.println("-- " + get_nome() + " --");
        System.out.println("Email: " + get_email());
    }
}
