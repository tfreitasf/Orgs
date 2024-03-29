# Orgs V4.1.0
Este é um aplicativo para gerenciar produtos. Com ele, é possível adicionar, editar e exibir uma lista de produtos, incluindo imagem, nome, descrição e preço. Além disso, é possível visualizar detalhes dos produtos,ordená-los de diferentes maneiras na lista e realizar operações de banco de dados de forma assíncrona para uma melhor performance.

## 🎥 Demonstração


<p float="left">  
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/9d35f605-a6c7-4dcf-b413-b57753c3ef9b" width="300" />
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/c53a911d-d97b-4660-92a6-1045ff966be7" width="300" />
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/aa2fd84b-40b6-4755-ae67-03a7b7844010" width="300" />
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/b4b3ec7a-6510-4759-a7dc-2ae5ac1ebaf9" width="300" />
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/8db346df-1627-460a-8b79-bc38ea7ee041" width="300" />
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/ee61504a-03bd-4643-a540-0aef0873aca7" width="300" /> 
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/950b06a8-6c49-4342-8d4f-068c1e181101" width="300" />
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/937bf4e5-4bb5-4fbe-ab93-fb85b8bf16dd" width="300" />
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/fa406ab8-9a26-40cc-ac64-d259b2a88181" width="300" />  
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/6a658eea-f5bb-428e-93d5-af4d76675522" width="300" />  
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/ff67a19f-13ac-4749-8ab2-3e46f855902f" width="300" /> 
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/90f98b00-184d-4201-85d4-6c4fee8d0516" width="300" /> 
</p>


## ✔️ Funcionalidades

- **Autenticação de Usuários**: Cadastro e login de usuários para acesso personalizado.
- **Lista Personalizada de Produtos**: Cada usuário visualiza e gerencia apenas seus próprios produtos cadastrados.
- **Cadastro de Produtos**: Adição de novos produtos com imagem, nome, descrição e preço.
- **Detalhes do Produto**: Visualização completa dos detalhes de cada produto cadastrado.
- **Edição e Exclusão de Produtos**: Funcionalidades acessíveis via cliques longos na lista ou através da tela de detalhes do produto.
- **Ordenação de Produtos**: Organização da lista de produtos por nome, descrição ou preço.
- **Operações Assíncronas de Banco de Dados**: Execução de todas as operações de banco de dados de maneira assíncrona para melhor performance e experiência do usuário.
- **Logout**: Opção para que o usuário possa encerrar sua sessão no aplicativo.
- **Tela de Perfil do Usuário**: Exibição de informações do usuário e opção para logout.


## 🌟 Novidades na versão 4.1.0

- **Visualização de Todos os Produtos**: Implementação da tela 'AllProductsActivity' para exibir todos os produtos e informações dos usuários relacionados.
- **Navegação para Detalhes do Produto**: Configuração do 'AllProductsAdapter' para permitir a navegação até os detalhes do produto selecionado.
- **Autocomplete em ProductFormActivity**: Adição de campo Autocomplete para facilitar a seleção de usuários ao salvar produtos sem usuário associado.
- **Ajustes nos Filtros de Produtos**: Os filtros na tela de lista de produtos agora consideram apenas os produtos do usuário logado.
- **Atualizações em AllProductsActivity**: Implementação do método onResume para garantir a atualização correta dos dados exibidos.


## 🚀 Futuras Implementações
- Sincronização com a nuvem para backup
- Recursos de compartilhamento de produtos
- Implementação de autenticação de usuários e controle de acesso.

## 🔨 Técnicas e tecnologias no projeto

- `Kotlin`: Linguagem de programação utilizada para desenvolver o aplicativo.
- `View Binding`: Busca de views do layout de forma segura.
- `Coil`: Carregar imagens via requisição HTTP.
- `Fontes personalizadas`: Configuração para adicionar novas fontes.
- `Extension functions`: Funções de extensão para carregar imagens e formatar valores em moeda.
- `Personalização de tema`: Modificação de cores para o tema do App.
- `Layout para Activities`:
  - `ContraintLayout`: ViewGroup padrão para implementar todos os layouts.
  - `ImageView`: View para apresentar imagens no App.
  - `Material Design Components`: Incluindo TextInputLayout para experiência de usuário melhorada.
  - `RecyclerView`: Listagem dos produtos.
  - `CardView`: Container para apresentar cada produto na lista.
  - `Button`: Botões interativos.
  - `AlertDialog`: Exibição de formulário para carregar novas imagens do produto.
- `Room Database`: Persistência de dados com Room para armazenar produtos e usuários.
- `Flow`: Uso de sequências assíncronas de dados.
- `setOnLongClickListener`: Gestos para clicks longos para edição e exclusão rápida de produtos.
- `PopupMenu`: Menus contextuais para ordenação de produtos.
- `DataStore`: Armazenamento de tipos primitivos, como o ID do usuário autenticado.
- `Migration`: Evolução do schema do banco de dados conforme as entidades do Room são modificadas.
- `Coroutines e Flow`: Comunicação assíncrona com Room e DataStore.
- `StateFlow`: Permite a alteração do valor de um Flow fora do seu builder.
- `Activity base`: Código comum entre Activities para gerenciamento de autenticação e sessão do usuário.
- `Relacionamento no Room`: Configuração de entidades para estabelecer relacionamentos, como produtos associados a usuários.

## 📝 Histórico de Versões

### v4.1.0
- Novas funcionalidades e melhorias na usabilidade.

### v4.0.0
- Lançamento das funcionalidades de autenticação e controle de produtos por usuário.


### v3.1.0
- Implementação de Coroutines e Flow.

### v3.0.2
- Adicionada a funcionalidade de ordenação de produtos na lista com várias opções de classificação.
- Melhorias de interface para facilitar a interação com a lista de produtos.

### v3.0.1
- Implementação de click longo para ações rápidas de edição e exclusão de produtos na lista.
- Adição de menus contextuais para edição e exclusão no RecyclerView.
- Refinamento da lógica de atualização da lista após operações CRUD.

### v3.0.0
- Lançamento das funcionalidades de edição e exclusão de produtos na tela de detalhes do produto.
- Melhorias na interface do usuário com menus contextuais e pop-ups.
- Adoção do Room para persistência de dados local.

### v2.0.1
- Lançamento da funcionalidade de tela de detalhes do produto.

### v2.0.0
- Lançamento da funcionalidade de adicão de imagens e gif's.
- Melhorias na interface do usuário atravez do Material Design.
- Formatação de Valores Monetários. A exibição de preços foi aprimorada com uma formatação adequada para valores monetários.

### v1.0.0
- Lançamento do aplicativo



## 📚 Curso e Instrução
Este projeto faz parte da Formação "Desenvolva seu primeiro app Android com Kotlin" ministrado pelo professor Alex Felipe e oferecido pela empresa Alura.

