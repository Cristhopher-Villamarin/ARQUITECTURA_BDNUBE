using System;
using System.Text;
using CLICON_EUREKA_GR08.ec.edu.monster.controller;

namespace CLICON_EUREKA_GR08.ec.edu.monster.view
{
    public class MainView
    {
        private static MainController mainController = new MainController();

        public static void Iniciar()
        {
            while (true)
            {
                if (mainController.GetUsuarioActual() == null)
                {
                    MenuLogin();
                }
                else
                {
                    MenuPrincipal();
                }
            }
        }

        private static void MenuLogin()
        {
            StringBuilder contenido = new StringBuilder();
            contenido.AppendLine("1. Iniciar Sesión");
            contenido.AppendLine("2. Salir");
            Console.WriteLine(EncloseInBox("EUREKABANK - RESTFULL", contenido.ToString()));
            Console.Write("Seleccione una opción: ");

            string opcionStr = Console.ReadLine();
            if (int.TryParse(opcionStr, out int opcion))
            {
                switch (opcion)
                {
                    case 1:
                        IniciarSesion();
                        break;
                    case 2:
                        Console.WriteLine("Gracias por usar nuestros servicios. ¡Hasta luego!");
                        Environment.Exit(0);
                        break;
                    default:
                        Console.WriteLine("Opción inválida. Intente nuevamente.");
                        break;
                }
            }
            else
            {
                Console.WriteLine("Opción inválida. Intente nuevamente.");
            }
        }

        private static void IniciarSesion()
        {
            Console.Write("Ingrese su nombre de usuario: ");
            string username = Console.ReadLine();
            Console.Write("Ingrese su contraseña: ");
            string password = Console.ReadLine();

            if (mainController.IniciarSesion(username, password))
            {
                Console.WriteLine("Inicio de sesión exitoso.");
            }
            else
            {
                Console.WriteLine("Credenciales incorrectas. Intente nuevamente.");
            }
        }

        private static void MenuPrincipal()
        {
            StringBuilder contenido = new StringBuilder();
            contenido.AppendLine("1. Realizar Depósito");
            contenido.AppendLine("2. Realizar Retiro");
            contenido.AppendLine("3. Realizar Transferencia");
            contenido.AppendLine("4. Ver Movimientos");
            contenido.AppendLine("5. Ver Datos Cuenta");
            contenido.AppendLine("6. Cerrar Sesión");
            Console.WriteLine(EncloseInBox("MENÚ PRINCIPAL", contenido.ToString()));
            Console.Write("Seleccione una opción: ");

            string opcionStr = Console.ReadLine();
            if (int.TryParse(opcionStr, out int opcion))
            {
                switch (opcion)
                {
                    case 1:
                        RealizarDeposito();
                        break;
                    case 2:
                        RealizarRetiro();
                        break;
                    case 3:
                        RealizarTransferencia();
                        break;
                    case 4:
                        VerMovimientos();
                        break;
                    case 5:
                        VerDatosCuenta();
                        break;
                    case 6:
                        CerrarSesion();
                        break;
                    default:
                        Console.WriteLine("Opción inválida. Intente nuevamente.");
                        break;
                }
            }
            else
            {
                Console.WriteLine("Opción inválida. Intente nuevamente.");
            }
        }

        private static void RealizarDeposito()
        {
            Console.Write("Ingrese el número de cuenta: ");
            string cuenta = Console.ReadLine();
            Console.Write("Ingrese el monto a depositar: ");
            string monto = Console.ReadLine();

            if (mainController.RealizarDeposito(cuenta, monto, "DEP", null))
            {
                Console.WriteLine("Depósito realizado con éxito.");
            }
            else
            {
                Console.WriteLine("Error al realizar el depósito.");
            }
        }

        private static void RealizarRetiro()
        {
            Console.Write("Ingrese el número de cuenta: ");
            string cuenta = Console.ReadLine();
            Console.Write("Ingrese el monto a retirar: ");
            string monto = Console.ReadLine();

            if (mainController.RealizarRetiro(cuenta, monto))
            {
                Console.WriteLine("Retiro realizado con éxito.");
            }
            else
            {
                Console.WriteLine("Error al realizar el retiro.");
            }
        }

        private static void RealizarTransferencia()
        {
            Console.Write("Ingrese el número de cuenta origen: ");
            string cuentaOrigen = Console.ReadLine();
            Console.Write("Ingrese el número de cuenta destino: ");
            string cuentaDestino = Console.ReadLine();
            Console.Write("Ingrese el monto a transferir: ");
            string monto = Console.ReadLine();

            if (mainController.RealizarTransferencia(cuentaOrigen, cuentaDestino, monto))
            {
                Console.WriteLine("Transferencia realizada con éxito.");
            }
            else
            {
                Console.WriteLine("Error al realizar la transferencia.");
            }
        }

        private static void VerMovimientos()
        {
            Console.Write("Ingrese el número de cuenta: ");
            string cuenta = Console.ReadLine();

            string resultado = mainController.VerMovimientos(cuenta);
            Console.WriteLine(resultado);
        }

        private static void VerDatosCuenta()
        {
            Console.Write("Ingrese el número de cuenta: ");
            string cuenta = Console.ReadLine();

            string resultado = mainController.VerDatosCuenta(cuenta);
            Console.WriteLine(resultado);
        }

        private static void CerrarSesion()
        {
            mainController.CerrarSesion();
            Console.WriteLine("Sesión cerrada exitosamente.");
        }

        // Utilidades de recuadro ASCII (coherentes con el estilo del controlador)
        private static string EncloseInBox(string title, string multilineContent)
        {
            string[] lines = multilineContent.Split(new[] { "\r\n", "\r", "\n" }, StringSplitOptions.None);
            int maxLineLength = title.Length;
            foreach (string line in lines)
            {
                if (line.Length > maxLineLength)
                {
                    maxLineLength = line.Length;
                }
            }
            int innerWidth = Math.Max(maxLineLength, title.Length);
            string horizontal = Repeat('-', innerWidth + 2);

            StringBuilder box = new StringBuilder();
            box.Append('+').Append(horizontal).Append("+").AppendLine();
            box.Append('|').Append(PadCenter(" " + title + " ", innerWidth + 2)).Append('|').AppendLine();
            box.Append('+').Append(horizontal).Append("+").AppendLine();
            foreach (string line in lines)
            {
                box.Append('|').Append(PadRight(" " + line, innerWidth + 2)).Append('|').AppendLine();
            }
            box.Append('+').Append(horizontal).Append('+');
            return box.ToString();
        }

        private static string Repeat(char ch, int count)
        {
            return new string(ch, count);
        }

        private static string PadRight(string text, int width)
        {
            if (text.Length >= width)
            {
                return text;
            }
            return text.PadRight(width);
        }

        private static string PadCenter(string text, int width)
        {
            if (text.Length >= width)
            {
                return text;
            }
            int totalPadding = width - text.Length;
            int left = totalPadding / 2;
            int right = totalPadding - left;
            return new string(' ', left) + text + new string(' ', right);
        }
    }
}

