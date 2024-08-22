# Servicio de Autenticacion

Webservice para la Autenticacion de Usuarios usando Spring Security y JWT

## PARA LA INSTALACION

Descargar el proyecto desde <github>

```bash
  git clone https://github.com/jhonilson/globallogic.git
```

cambiar a la carpeta globallogic
```bash
  cd globallogic
```

Construir el proyecto
```bash
./gradlew build
```
Construir el proyecto
```bash
./gradlew bootRun
```

## PARA LAS PRUEBAS
Para el Endpoint SignUp:
- POST 'http://localhost:8080/api/sign-up'

```
curl --location 'http://localhost:8080/api/sign-up' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "password": "Password123",
    "phones": [
        {
            "number": 984969558,
            "cityCode": 1,
            "countryCode": "+56"
        }
    ]
}'
```

Una vez creado el usuario, se genera un TOKEN

Para el endpoint Login (Inicio de sesión) 
- POST 'http://localhost:8080/api/login'

```
curl --location --request POST 'http://localhost:8080/api/login' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsImlhdCI6MTcyMjk3NTE0NywiZXhwIjoxNzIyOTc1MzI3fQ.bvxhEIhGC_Xa-X8yXD14rhZFkk4pWH99wz51prl73VY'
```
```
curl --location 'http://localhost:8080/api/sign-up' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "e3a8a760-7665-4ce9-be16-565819626a75",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "password": "$2a$10$2w6z7z7z7z7z7z7z7z7z7uIDtwGx3LA.Kd3D47TOdmlo3DZgM0ZCa",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsImlhdCI6MTcyMjk3NTE1OCwiZXhwIjoxNzIyOTc1MzM4fQ.Xlxz9_fVYM_TQF-2MHqVGvsRZDS_a1PLsD0OkUlNOXg",
    "created": "Aug 06, 2024 04:12:27 PM",
    "lastLogin": "Aug 06, 2024 04:12:27 PM",
    "isActive": true,
    "phones": [
        {
            "id": 1,
            "number": 984969558,
            "cityCode": 1,
            "countryCode": "+56"
        }
    ]
}'
```

# DIAGRAMA UML

## Diagrama de Componentes
![image](https://github.com/user-attachments/assets/15332f8a-91a0-4366-af14-717657dfa297)

## Diagrama de Secuencias
![image](https://github.com/user-attachments/assets/ca5ffee1-7f58-475a-9cf8-20a7d490eb5b)


### SWAGGER
http://localhost:8080/swagger-ui/index.html
