using Microsoft.Data.SqlClient;

namespace ec.edu.monster.db;

public class AccesoBD
{
    public static SqlConnection GetConnection(IConfiguration configuration)
    {
        string? connectionString = configuration.GetConnectionString("EurekaBankDB");
        
        if (string.IsNullOrEmpty(connectionString))
        {
            throw new Exception("Connection string 'EurekaBankDB' not found in configuration.");
        }

        try
        {
            var connection = new SqlConnection(connectionString);
            return connection;
        }
        catch (Exception ex)
        {
            throw new Exception($"Error al conectar a SQL Server: {ex.Message}", ex);
        }
    }

    public static void CloseConnection(SqlConnection? connection)
    {
        if (connection != null)
        {
            try
            {
                connection.Close();
                Console.WriteLine("Database connection closed.");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error closing connection: {ex.Message}");
            }
        }
    }
}

