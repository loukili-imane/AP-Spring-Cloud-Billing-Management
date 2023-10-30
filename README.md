# AP-Spring-Cloud-Billing-Management
Architecture du projet
![image](https://github.com/loukili-imane/Architecture-Microservice-Billing-Management-Spring-Cloud/assets/93887037/3b6eec85-03b2-4077-81e5-6e639c4729e9)

1. Créer le micro service Customer-service
    1. Créer l’entité Customer
    2. Créer l’interface CustomerRepository basée sur Spring Data
    3. Déployer l’API Restful du micro-service en utilisant Spring Data Rest
    4. Tester le Micro service
2. Créer le micro service Inventory-service
    1. Créer l’entité Product
    2. Créer l’interface ProductRepository basée sur Spring Data
    3. Déployer l’API Restful du micro-service en utilisant Spring Data Rest
    4. Tester le Micro service
3. Créer la Gateway service en utilisant Spring Cloud Gateway
    1. Tester la Service proxy en utilisant une configuration Statique basée sur le fichier application.yml
    2. Tester la Service proxy en utilisant une configuration Statique basée une configuration Java
4. Créer l’annuaire Registry Service basé sur NetFlix Eureka Server
5. Tester le proxy en utilisant une configuration dynamique de Gestion des routes vers les micro services enregistrés dans l’annuaire Eureka Server
6. Créer Le service Billing-Service en utilisant Open Feign pour communiquer avec les services Customer-service et Inventory-service
7. Créer un client Angular qui permet d’afficher une facture
