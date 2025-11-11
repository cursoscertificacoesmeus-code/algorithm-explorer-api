# 🚀 Explorador de Algoritmos API (Algorithm Explorer API)

Projeto acadêmico que fornece a API de backend para o **Explorador de Algoritmos**. Esta API é responsável por processar operações de algoritmos de ordenação e manipulação de grafos, servindo como o cérebro por trás da aplicação web interativa.

## 📖 Descrição e Funcionalidades

A API do Algorithm Explorer oferece os seguintes serviços e lógica de negócio:

* **Algoritmos de Ordenação:** Recebe conjuntos de dados e aplica algoritmos de ordenação clássicos, retornando os passos e o resultado final para análise visual no frontend.
* **Operações em Grafos:** Permite a criação, manipulação e execução de algoritmos de grafos (como Busca em Largura, Dijkstra, etc.) em estruturas de grafos fornecidas, utilizando a biblioteca JGraphT.
* **Autenticação (JPA):** Gerencia o cadastro e login de usuários, utilizando JPA para persistência de dados.

A arquitetura é baseada em microserviços, com um frontend desacoplado (Vue.js), e toda a infraestrutura é hospedada na nuvem da Microsoft Azure.

---

## 🛠️ Arquitetura e Tecnologias

A API é construída com:

| Categoria | Tecnologia | Versão |
| :--- | :--- | :--- |
| **Linguagem** | Java | 21 |
| **Framework** | Spring Boot | 3.x |
| **Persistência** | Spring Data JPA | Integrado |
| **Banco de Dados**| MySQL | 8.0+ |
| **Biblioteca de Grafos** | JGraphT | Latest |
| **Gerenciamento** | Apache Maven | 3.6+ |
| **Hospedagem (Planejada)** | Azure App Service | - |

---

## 💾 Configuração do Banco de Dados (JPA)

Esta API requer uma instância do MySQL rodando remotamente. A conexão está configurada para buscar variáveis de ambiente no Azure App Service.

### Conexão Local

Para desenvolvimento local, as configurações do banco devem ser adicionadas ao `src/main/resources/application.properties` ou `application.yml`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update # Use 'create' ou 'create-drop' para o primeiro uso
```
### Conexão em Produção (Azure)
Para o deploy no Azure App Service, as configurações de conexão devem ser definidas como Variáveis de Aplicação (Application Settings) no portal do Azure, apontando para o IP da sua VM (Servidor MySQL):

```
SPRING_DATASOURCE_URL = jdbc:mysql://4.174.129.224:3306/algoritmosdb
SPRING_DATASOURCE_USERNAME = azureuser
SPRING_DATASOURCE_PASSWORD = [Sua Senha Segura]
```

### ⚙️ Como Executar Localmente
Para executar a API localmente, siga os passos abaixo:

1. **Pré-requisitos:**

* **Java Development Kit (JDK) 21 ou superior**

* **Apache Maven 3.6.0 ou superior**

* **Servidor MySQL rodando localmente (ou a VM do Azure acessível).**

2. **Clonar o Repositório:**

```
  git clone [https://github.com/cursoscertificacoesmeus-code/algorithm-explorer-api.git](https://github.com/cursoscertificacoesmeus-code/algorithm-explorer-api.git)
  cd api-algoritmo-explorer
```
3. **Compilar e Executar:**
```
# Limpa, instala as dependências e compila o projeto
mvn clean install

# Executa a aplicação Spring Boot
mvn spring-boot:run
```
**A API estará disponível em** http://localhost:8080

### 👥 Autor
* **Rodrigo Marcos Nogueira Pestana**