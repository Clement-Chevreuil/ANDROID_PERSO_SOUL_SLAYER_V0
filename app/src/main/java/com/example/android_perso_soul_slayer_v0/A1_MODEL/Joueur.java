package com.example.android_perso_soul_slayer_v0.A1_MODEL;

import java.util.List;

public class Joueur {

    public int id;
    private String nom;
    public int vie_max;
    public int vie;
    public int mana_max;
    public int mana;
    public int attaque;
    public int etoiles;
    public int argent;
    public int experience;
    public int niveau;
    public List<MonEquipement> monEquipementList;
    public String password;
    int endurance;
    int endurance_max;
    int defense;
    int precision;
    int vitesse;

    //int force;
    //FUTURE
    //nbattaque
    //defense
    //attaquemagique
    //taux reussite

    //FOR MONSTER
    public Joueur(int id, String nom,int vie_max, int vie, int mana_max, int mana, int experience, int attaque, int etoiles) {
        this.id = id;
        this.nom = nom;
        this.vie_max = vie_max;
        this.vie = vie;
        this.attaque = attaque;
        this.etoiles = etoiles;
        this.mana_max = mana_max;
        this.mana = mana;
        this.experience = experience;
    }

    //MON JOUEUR
    public Joueur(String nom,int vie_max, int vie, int mana_max, int mana, int endurance_max, int endurance, int attaque, int defense, int precision, int vitesse, int experience, int argent) {
        this.nom = nom;
        this.vie_max = vie_max;
        this.vie = vie;
        this.mana_max = mana_max;
        this.mana = mana;
        this.endurance_max = endurance_max;
        this.endurance = endurance;
        this.attaque = attaque;
        this.defense = defense;
        this.vitesse = vitesse;
        this.precision = precision;
        this.experience = experience;
        this.argent = argent;

        if(experience != 0)
        {
            adaptNiveau();
        }
    }

    //CREATION RAPIDE DE LIAISON SORT - EQUIPEMENT
    public Joueur(int id) {this.id = id;}
    public Joueur() {}

    //GETTER
    public int getVie_max() { return vie_max; }
    public int getId() { return id; }
    public int getStars() { return etoiles; }
    public String getNom() { return nom; }
    public int getVie() { return vie; }
    public int getExperience() { return experience; }
    public int getAttaque() { return attaque; }
    public int getArgent() { return argent; }
    public List<MonEquipement> getEquipementList() { return monEquipementList; }
    public int getMana() { return mana; }
    public int getMana_max() { return mana_max; }
    public int getNiveau() { return niveau; }
    public int getDefense() { return defense; }
    public int getEndurance() { return endurance;}
    public int getPrecision() { return precision; }
    public int getVitesse() { return vitesse; }
    public String getPassword() { return password; }
    public int getEndurance_max() {return endurance_max;}

    //SETTER
    public void setVie_max(int vie_max) { this.vie_max = vie_max; }
    public void setId(int id) { this.id = id; }
    public void setStars(int etoiles) { this.etoiles = etoiles; }
    public void setNom(String nom) { this.nom = nom; }
    public void setVie(int vie) { this.vie = vie; }
    public void setAttaque(int attaque) { this.attaque = attaque; }
    public void setexperience(int experience) { this.experience = experience; adaptNiveau(); }
    public void setArgent(int argent) { this.argent = argent; }
    public void setMonEquipementList(List<MonEquipement> monEquipementList) { this.monEquipementList = monEquipementList; }
    public void setMana(int mana) { this.mana = mana; }
    public void setMana_max(int mana_max) { this.mana_max = mana_max; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setEndurance(int endurance) { this.endurance = endurance; }
    public void setNiveau(int niveau) { this.niveau = niveau; }
    public void setPrecision(int precision) { this.precision = precision; }
    public void setVitesse(int vitesse) { this.vitesse = vitesse; }
    public void setPassword(String password) { this.password = password;}
    public void setEndurance_max(int endurance_max) {this.endurance_max = endurance_max;}

    public Joueur createMonster2(int id)
    {
        Joueur monster;

        if(id == 2)
        {
            monster = new Joueur(2,"GOBLIN",1,1,0,0,1,1,1);
        }
        else if(id == 3)
        {
            monster = new Joueur(3,"GOBLIN ARCHER",2,2,0,0,2,3,1);
        }
        else if(id == 4)
        {
            monster = new Joueur(4,"GOBLIN EPEISTE",3,3,1,1,2,2,1);
        }
        else if(id == 5)
        {
            monster = new Joueur(5,"GOBLIN MAGE",3,3,3,3,3,1,1);
        }
        else if(id == 6)
        {
            monster = new Joueur(6,"GOBLIN TROUPE",5,5,0,0,5,5,2);
        }
        else if(id == 7)
        {
            monster = new Joueur(7,"HOBGOBLIN",8,8,0,0,4,4,2);
        }
        else if(id == 8)
        {
            monster = new Joueur(8,"GOBLIN PALADIN",1,1,1,1,1,1,1);
        }
        else if(id == 9)
        {
            monster = new Joueur(9,"GOBLIN CHAMPION",1,1,1,1,1,1,1);
        }
        else if(id == 10)
        {
            monster = new Joueur(10,"GOBLIN SEIGNEUR",1,1,1,1,1,1,1);
        }
        else
        {
            monster = new Joueur(99,"ERROR",999,999,999,999,999,999,5);
        }
        return monster;
    }

    public void adaptEquip()
    {
        for (MonEquipement equip : this.monEquipementList) {
            if(equip.equip == 1)
            {
                if(equip.getEquipement().getAttribut().equals("attaque"))
                {
                    this.attaque = this.attaque + equip.getEquipement().getGain();
                }
                else if(equip.getEquipement().getAttribut().equals("vie"))
                {
                    this.vie = this.vie + equip.getEquipement().getGain();
                }
            }
        }
    }

    public int adaptNiveau()
    {
        int experience = this.getEndurance();


        if(experience >= 10 && experience < 20) {
            niveau = 1;
            attaque = attaque + 1;
        }
        else if(experience >= 20 && experience < 40) {
            attaque = attaque + 2;
            niveau = 2;
        }
        else if(experience >= 40) {
            attaque = attaque + 3;
            niveau = 3;
        }
        else if(experience >= 50) {
            attaque = attaque + 4;
            niveau = 4;
        }
        else{
            niveau = 0;
        }

        return niveau;
    }


}
