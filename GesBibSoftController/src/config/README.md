# Uso

Duplicar el archivo *simplejavamail.properties.example* y llamarlo *simplejavamail.properties*. Dentro de este poner la información del servidor SMTP y las credenciales del usuario

## Ejemplo

``` properties
# Para un usuario Gmail
simplejavamail.smtp.username=#USUARIO
simplejavamail.smtp.password=#CONTRASEÑA
simplejavamail.smtp.host=smtp.gmail.com
simplejavamail.smtp.port=25

# Para especificar de quien se envia el correo
simplejavamail.defaults.from.name=GesBib Admin
simplejavamail.defaults.from.address=admin@gesbib.pucp.edu.pe
```

Se tiene que permitir el uso de aplicaciones menos seguras desde la cuenta donde se vaya a enviar