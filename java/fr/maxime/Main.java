package fr.maxime;

import fr.maxime.dao.EtudientDAO;
import fr.maxime.entity.Etudient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static EntityManager entityManager;

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_personne");
        entityManager = entityManagerFactory.createEntityManager();
        EtudientDAO etudientDAO = new EtudientDAO(entityManager);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("1. voir tout les etudient");
            System.out.println("2. Ajouter un etudiant");
            System.out.println("3. update un etudiant");
            System.out.println("4. Supprimer un etudiant");
            System.out.println("5. Quitter");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    for (Etudient etudient : etudientDAO.read()){
                        System.out.println("id: " + etudient.getId() + " nom : " + etudient.getNom() + " prenom : " + etudient.getPrenom() + " classe : " + etudient.getClasse());
                    }
                    break;
                case 2:
                    System.out.println("Qu'elle et son nom:");
                    String nom = scanner.nextLine();
                    System.out.println("Qu'elle et son prenom:");
                    String prenom = scanner.nextLine();
                    System.out.println("Qu'elle et son classe:");
                    String classe = scanner.nextLine();
                    try{
                        etudientDAO.create(Etudient.builder().nom(nom).prenom(prenom).classe(classe).build());
                        System.out.println("etudient créé");
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    for (Etudient etudient : etudientDAO.read()){
                        System.out.println("id: " + etudient.getId() + " nom : " + etudient.getNom() + " prenom : " + etudient.getPrenom() + " classe : " + etudient.getClasse());
                    }
                    System.out.println("Qu'elle et l'id de létudient a modifier:");
                    int modifier = scanner.nextInt();
                    scanner.nextLine();
                    Etudient updateEtudient = entityManager.find(Etudient.class, modifier);
                    if (updateEtudient == null){
                        System.out.println("Erreur : etudient n'existe pas");
                        break;
                    }
                    System.out.println("Qu'elle et son nom:");
                    updateEtudient.setNom(scanner.nextLine());
                    System.out.println("Qu'elle et son prenom:");
                    updateEtudient.setPrenom(scanner.nextLine());
                    System.out.println("Qu'elle et son classe:");
                    updateEtudient.setClasse(scanner.nextLine());
                    etudientDAO.update(updateEtudient);
                    System.out.println("etudient update");
                    break;
                case 4:
                    for (Etudient etudient : etudientDAO.read()){
                        System.out.println("id: " + etudient.getId() + " nom : " + etudient.getNom() + " prenom : " + etudient.getPrenom() + " classe : " + etudient.getClasse());
                    }
                    System.out.println("Qu'elle et l'id de létudient a supprimer:");
                    int suppression = scanner.nextInt();
                    scanner.nextLine();
                    Etudient deleteEtudient = entityManager.find(Etudient.class, suppression);
                    if (deleteEtudient == null){
                        System.out.println("Erreur : etudient n'existe pas");
                        break;
                    }
                    etudientDAO.delete(deleteEtudient);
                    System.out.println("etudient supprimer");
                    break;
                case 5:
                    exit = !exit;
            }
        }
    }
    
}