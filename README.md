# Orgs V3.0.0
Este é um aplicativo para gerenciar produtos. Com ele, é possível adicionar, editar e exibir uma lista de produtos, incluindo imagem, nome, descrição e preço. Também, é possível cadastrar produtos e visualizar a lista de produtos e uma tela de detalhe do produto.

## 🎥 Demonstração

<p float="center">
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/26dcaf85-f3db-43b3-8497-209eaa378729" width="350" />
</p>


<p float="left">  
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/e177968e-c5eb-48b3-ba03-64e523fcacf9" width="300" />
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/ee61504a-03bd-4643-a540-0aef0873aca7" width="300" /> 
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/950b06a8-6c49-4342-8d4f-068c1e181101" width="300" />
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/937bf4e5-4bb5-4fbe-ab93-fb85b8bf16dd" width="300" />
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/810df5e3-a4f2-4097-a6f0-e28d2ef8913f" width="300" />
  <img src="https://github.com/tfreitasf/Orgs/assets/83042767/6a658eea-f5bb-428e-93d5-af4d76675522" width="300" />  
</p>



## ✔️ Funcionalidades
- Adicionar um novo produto
- Exibir a lista de produtos
- Exibir tela de detalhes do produto.
- Edição e Exclusão de Produtos da Lista.

## 🌟 Novidades na versão 3.0.0
- **Edição de Produtos**: Agora é possível editar as informações dos produtos diretamente na tela de detalhes.
- **Exclusão de Produtos**: Os produtos podem ser excluídos facilmente através de um menu de contexto na lista ou na tela de detalhes.
- **Melhorias de Interface**: A interface do usuário foi aprimorada com a adição de menus contextuais e pop-ups para uma navegação mais intuitiva.
- **Persistência de Dados com Room**: Introduzimos o Room para uma gestão de banco de dados robusta e eficiente.


## 🚀 Futuras Implementações
- Sincronização com a nuvem para backup
- Recursos de compartilhamento de produtos
- Implementação de filtros e buscas na lista de produtos
- Melhorias no código para que as operações de banco de dados não sejam executadas na thread principal 

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




## 📚 Curso e Instrução
Este projeto faz parte do curso "Android com Kotlin: personalize o seu app" ministrado pelo professor Alex Felipe e oferecido pela empresa Alura.

