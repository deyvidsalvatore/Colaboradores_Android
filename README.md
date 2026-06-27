# Gerenciamento Colaboradores

Sistema desenvolvido em Android nativo com Jetpack Compose utilizando os principios da Clean Architecture e Material Design 3 para o gerenciamento de colaboradores de uma organizacao.

## Arquitetura do Projeto

O projeto segue uma estrutura baseada em camadas para garantir o desacoplamento do modelo de negocio em relacao aos frameworks de interface e componentes do Android.

* **Domain**: Contem as entidades de negocio e os casos de uso da aplicacao, sendo 100% puro Kotlin e livre de dependencias da UI.
* **UI**: Camada de apresentacao responsavel pelo gerenciamento de estado e renderizacao dos componentes visuais com Jetpack Compose.

## Funcionalidades

* Cadastro de novos colaboradores com validacao em tempo real.
* Listagem de colaboradores utilizando cards personalizados com identificacao visual por setores.
* Edicao de dados cadastrais de colaboradores existentes.
* Remocao de colaboradores com modal de confirmacao de exclusao.

## Regras de Validacao do Formulario

* **Campos Obrigatorios**: Os campos de Nome e E-mail nao podem ser salvos vazios ou apenas com espacos em branco.
* **Tamanho do Nome**: O campo Nome exige uma quantidade minima de 3 caracteres e limite maximo de 50 caracteres.
* **Caracteres do Nome**: Nao e permitida a insercao de valores numericos no campo Nome.
* **Formato do E-mail**: O campo E-mail valida a estrutura do texto para garantir um dominio correto conforme o padrao exemplo@email.com.

## Setores Disponiveis (Niveis)

Os colaboradores sao categorizados em quatro setores com identificacoes visuais unicas na interface:
* Administrativo
* Financeiro
* Gerencia
* Suporte

## Tecnologias Utilizadas

* Android SDK
* Kotlin
* Jetpack Compose
* Material Design 3
* StateFlow e SharedFlow para gerenciamento de estados e efeitos de UI
* ViewModel (Architecture Components)