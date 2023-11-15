# Orgs V3.0.2
Este é um aplicativo para gerenciar produtos. Com ele, é possível adicionar, editar e exibir uma lista de produtos, incluindo imagem, nome, descrição e preço. Além disso, é possível visualizar detalhes dos produtos e agora ordená-los de diferentes maneiras na lista.

## 🎥 Demonstração

<p float="center">
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/26dcaf85-f3db-43b3-8497-209eaa378729" width="350" />
</p>


<p float="left">  
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/a9d78c9f-ac16-44a4-ace9-bbe52ab4ff9e" width="300" />
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/ee61504a-03bd-4643-a540-0aef0873aca7" width="300" /> 
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/950b06a8-6c49-4342-8d4f-068c1e181101" width="300" />
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/937bf4e5-4bb5-4fbe-ab93-fb85b8bf16dd" width="300" />
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/810df5e3-a4f2-4097-a6f0-e28d2ef8913f" width="300" />
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/6a658eea-f5bb-428e-93d5-af4d76675522" width="300" />  
</p>




## ✔️ Funcionalidades
- Cadastro de novos produtos.
- Visualização da lista de produtos.
- Acesso a detalhes dos produtos.
- Edição e exclusão de produtos através de cliques longos na lista e opções na tela de detalhes.
- Ordenação dos produtos na lista por nome, descrição ou preço.

## 🌟 Novidades na versão 3.0.2
- **Ordenação de Produtos**: Implementada a funcionalidade de ordenar produtos na lista por nome, descrição e preço em ordem ascendente ou descendente.


## 🚀 Futuras Implementações
- Sincronização com a nuvem para backup
- Recursos de compartilhamento de produtos
- Execução de operações de banco de dados fora da thread principal para melhor performance.

## 🔨 Técnicas e tecnologias no projeto

- `Kotlin`: Linguagem de programação utilizada para desenvolver o aplicativo.
- `View Binding`: busca de views do layout de forma segura
- `Coil`: carregar imagens via requisição HTTP
- `Fontes personalizadas`: configuração para adicionar novas fontes
- `Extension functions`: adicionar comportamentos em outras classes para reutilizá-los como funções de extensão para carregar imagens e formatar valores em moeda.
- `Personalização de tema`: modificação de cores para o tema do App
- Layout para Activities
  - `ContraintLayout` : ViewGroup padrão para implementar todos os layouts
  - `ImageView`: View para apresentar imagens no App
  - `Material Design Components`: Incluindo TextInputLayout para uma experiência de usuário melhorada e interfaces mais intuitivas.
  - `RecyclerView`: listagem das produtos
  - `CardView`: container para apresentar cada produto na lista de produtos 
  - `Button` :Botões interativos.
  - `AlertDialog`: Exibição de formulário para carregar novas imagens do produto
- `Room Database`: Persistência de dados com Room para armazenar os produtos localmente.
- `setOnLongClickListener`: Utilização de gestos para clicks longos para ativação de popupMenu que facilitam a edição e exclusão rápida de produtos.
- `PopupMenu`: Utilização de menus contextuais para oferecer opções de ordenação de produtos na lista.

## 📝 Histórico de Versões

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
Este projeto faz parte do curso "Android com Kotlin: personalize o seu app" ministrado pelo professor Alex Felipe e oferecido pela empresa Alura.

