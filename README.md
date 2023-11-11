# Orgs
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
</p>



## ✔️ Funcionalidades
- Adicionar um novo produto
- Exibir a lista de produtos
- Exibir tela de detalhes do produto.

## 🌟 Novas Implementações
A versão mais recente do Orgs traz uma série de melhorias e novidades que melhoram a experiência do usuário:

### Adição de Imagens
- Agora é possível adicionar imagens aos produtos, incluindo suporte a **GIFs animados**, o que torna a visualização dos produtos mais dinâmica e atraente.

### Aprimoramento de Layout com Material Design
- O layout foi completamente revitalizado seguindo os princípios do **Material Design**. Isso inclui a utilização de `TextInputLayout` para campos de entrada e `CardView` para a exibição de produtos, proporcionando uma interface mais limpa e intuitiva.

### Formatação de Valores Monetários
- A exibição de preços foi aprimorada com uma formatação adequada para valores monetários, garantindo uma leitura fácil e compreensível dos custos associados a cada produto.


## 🚀 Futuras Implementações
- Persistência de dados com Room
- Editar informações do produto
- Excluir produtos 

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




## 📚 Curso e Instrução
Este projeto faz parte do curso "Android com Kotlin: personalize o seu app" ministrado pelo professor Alex Felipe e oferecido pela empresa Alura.

