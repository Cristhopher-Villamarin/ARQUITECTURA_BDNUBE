var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var lessMiddleware = require('less-middleware');
var logger = require('morgan');
var bodyParser = require('body-parser');

const authService = require('./services/auth-service');
const MovementService = require('./services/movement-service');
const CuentaService = require('./services/cuenta-service');

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');

var app = express();

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(cookieParser());
app.use(lessMiddleware(path.join(__dirname, 'public')));
app.use(express.static(path.join(__dirname, 'public')));
app.use(express.static(path.join(__dirname, 'views')));

app.use('/', indexRouter);
app.use('/users', usersRouter);

app.get('/health', (req, res) => {
    res.json({ 
        status: 'OK', 
        timestamp: new Date().toISOString(),
        message: 'Eureka Bank Web Application is running'
    });
});

app.get('/login', (req, res) => {
    res.sendFile(path.join(__dirname, 'views', 'login.html'));
});

app.get('/home', (req, res) => {
    res.sendFile(path.join(__dirname, 'views', 'home.html'));
});

app.get('/welcome', (req, res) => {
    // Redirigir a home para mantener compatibilidad
    res.redirect('/home');
});

app.post('/login', async (req, res) => {
    const { username, password } = req.body;
    try {
        const isAuthenticated = await authService.authenticate(username, password);
        res.json({ success: isAuthenticated });
    } catch (error) {
        res.status(500).json({ success: false, message: 'Authentication error' });
    }
});

app.get('/logout', (req, res) => {
    res.redirect('/login');
});

app.get('/cuenta', (req, res) => {
    res.sendFile(path.join(__dirname, 'views', 'cuenta.html'));
});

app.get('/deposito', (req, res) => {
    res.sendFile(path.join(__dirname, 'views', 'deposito.html'));
});

app.get('/retiro', (req, res) => {
    res.sendFile(path.join(__dirname, 'views', 'retiro.html'));
});

app.get('/transferencia', (req, res) => {
    res.sendFile(path.join(__dirname, 'views', 'transferencia.html'));
});

app.get('/movimiento', (req, res) => {
    res.sendFile(path.join(__dirname, 'views', 'movimiento.html'));
});

// Rutas legacy para mantener compatibilidad
app.get('/account', (req, res) => {
    res.redirect('/cuenta');
});

app.get('/movements', (req, res) => {
    res.redirect('/movimiento');
});

app.post('/deposit', async (req, res) => {
    const { cuenta, monto, tipo, cd } = req.body;

    console.log(`Received deposit request: cuenta=${cuenta}, monto=${monto}`);

    try {
        if (!cuenta || !monto) {
            throw new Error("Missing required fields: 'cuenta' or 'monto'");
        }

        const result = await CuentaService.deposit(cuenta, monto, tipo, cd);
        console.log('SOAP service result:', result);

        res.json({ success: true, result });
    } catch (error) {
        console.error('Error in /deposit route:', error);

        res.status(500).json({
            success: false,
            message: 'Deposit error',
            error: error.message,
        });
    }
});

app.post('/movementsR', async (req, res) => {
    const { numcuenta } = req.body;
    console.log('Received account number:', numcuenta);

    try {
        const movements = await MovementService.getMovimientos(numcuenta);
        res.json({ success: true, movements });
    } catch (error) {
        console.error('Full Movement Retrieval Error:', error);
        res.status(500).json({
            success: false,
            message: 'Movement retrieval error',
            error: error.message,
            details: error.toString()
        });
    }
});

app.post('/accountDetails', async (req, res) => {
    const { numcuenta } = req.body;
    console.log('Received account number for details:', numcuenta);

    try {
        if (!numcuenta) {
            throw new Error("Missing required field: 'numcuenta'");
        }

        const account = await CuentaService.verDatosCuenta(numcuenta);
        console.log('SOAP service account details:', account);

        res.json({ success: true, account });
    } catch (error) {
        console.error('Error in /accountDetails route:', error);
        res.status(500).json({
            success: false,
            message: 'Account details retrieval error',
            error: error.message,
            details: error.toString()
        });
    }
});

app.use(function(req, res, next) {
  next(createError(404));
});

app.use(function(err, req, res, next) {
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
