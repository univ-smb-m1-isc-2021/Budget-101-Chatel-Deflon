# Budget-101-Chatel-Deflon

**Participants :**

- Elodie Deflon
- Antoine Chatel

## Description

Api servant le repository ````budget-101-chatel-deflon_angular```` application de gestion de budget

## Application

Angular : https://github.com/univ-smb-m1-isc-2021/budget-101-chatel-deflon_angular

---

## Services

- **Utilisateur**
  - Enregistrer un nouvel utilisateur
  - Connexion
  - Editer son mail (modif mdp impossible manque de temps du à l'authentification)
- **Budget**
    - Création / Edition / Suppression de bugets
- **Transactions (Expense)**
  - Création / Edition / Suppression de transactions
  - 3 différents types de transactions disponibles:
    - Ponctuelle (transaction à une date précise)
    - Récurrente (transaction qui se répète à une fréquence donnée)
    - Etalée (transaction étalée entre une date de début et une date de fin)
- **Mail**
  - Envoie d'un mail récapitulatif des toutes les transactions par budget
___
## Arborescence

Un nom en _italique_ représente un package java.

**main**

- java
  - _budget_
    - _adapters_
      - _request_template_ (templates représentant les données échangées)
      - web.security (gestion d'authentification)
      - BudgetController
      - ExpenseController
      - MailController
      - UserController
    - _application_ (services pour chaque entités)
    - _mail_
      - EmailSenderService (gestion de l'envoie de mail)
    - _persistence_ (entitées + interfaces repository)
      - _budget_
      - _expense_
      - _user_
      - Initializer
    - Application (SpringBoot)
- resources
  - application.properties

**test**

- java
  - _budget_
    - _adapters_
      - UserControllerTest (n'a pas aboutis car mocker l'auth est difficile)
    - _application_
      - BudgetServiceTest
      - ExpenseServiceTest
      - UserServiceTest
___
### Sonarcloud :
https://sonarcloud.io/project/overview?id=univ-smb-m1-isc-2021_Budget-101-Chatel-Deflon
