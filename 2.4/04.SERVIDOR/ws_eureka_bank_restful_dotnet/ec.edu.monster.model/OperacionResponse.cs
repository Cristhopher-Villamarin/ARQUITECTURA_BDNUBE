namespace ec.edu.monster.model;

public class OperacionResponse
{
    public bool Success { get; set; }
    public double Saldo { get; set; }
    public string? Mensaje { get; set; }

    public OperacionResponse()
    {
    }

    public OperacionResponse(bool success, double saldo, string? mensaje)
    {
        Success = success;
        Saldo = saldo;
        Mensaje = mensaje;
    }
}

