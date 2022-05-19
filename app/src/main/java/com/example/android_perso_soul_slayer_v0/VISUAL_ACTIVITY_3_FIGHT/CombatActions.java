package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_3_FIGHT;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.android_perso_soul_slayer_v0.A1_MODEL.Joueur;
import com.example.android_perso_soul_slayer_v0.A1_MODEL.MonEquipement;
import com.example.android_perso_soul_slayer_v0.A1_MODEL.MonSort;
import com.example.android_perso_soul_slayer_v0.A1_MODEL.Quete;
import com.example.android_perso_soul_slayer_v0.A1_MODEL.Sort;
import com.example.android_perso_soul_slayer_v0.ACTIVITY.Menu;
import com.example.android_perso_soul_slayer_v0.A2_DAO.JoueurDAO;
import com.example.android_perso_soul_slayer_v0.A2_DAO.EquipementDAO;
import com.example.android_perso_soul_slayer_v0.A2_DAO.MonEquipementDAO;
import com.example.android_perso_soul_slayer_v0.A2_DAO.QueteDAO;
import com.example.android_perso_soul_slayer_v0.R;

public class CombatActions {

    Context context;
    Activity activity;
    TextView informations, ennemieNom, joueurNom, numberEnemyvie,pointVieJoueur, pointManaEnnemie, pointManaJoueur;
    ProgressBar ennemieVie, joueurVie, ennemieMana, joueurMana;
    JoueurDAO joueurDAO;
    Joueur Joueur, enemy;
    MonEquipementDAO monEquipementDAO;
    EquipementDAO equipementDAO;
    ViewPager viewPager;

    int joueurAtk;

    Button punch;
    ListView list;
    Quete quete;
    QueteDAO queteDAO;
    int questId;
    int monsterInformation;
    FragmentManager support;

    String text1;
    String text2;
    String text3;
    int text2duration;


    public CombatActions(Activity activity, Context context, FragmentManager support)
    {
        this.activity = activity;
        this.context = context;

        informations = activity.findViewById(R.id.Informations);
        joueurVie = activity.findViewById(R.id.VieJoueur);
        pointVieJoueur = activity.findViewById(R.id.PointVieJoueur);
        numberEnemyvie = activity.findViewById(R.id.PointVieEnnemie);
        pointManaEnnemie =  activity.findViewById(R.id.PointManaEnnemie);
        pointManaJoueur =  activity.findViewById(R.id.PointManaJoueur);
        ennemieVie =  activity.findViewById(R.id.VieEnnemie);
        ennemieMana = activity.findViewById(R.id.ManaEnnemie);
        joueurMana = activity.findViewById(R.id.ManaJoueur);
        ennemieNom =  activity.findViewById(R.id.NomEnnemie);
        joueurNom =  activity.findViewById(R.id.NomJoueur);
        viewPager = activity.findViewById(R.id.view_pager);
        punch = activity.findViewById(R.id.Frappe);

        list = activity.findViewById(R.id.ListObjet);

        joueurDAO = new JoueurDAO(context);
        Joueur = joueurDAO.getMyJoueur();
        monEquipementDAO = new MonEquipementDAO(context);
        equipementDAO = new EquipementDAO(context);

        this.support = support;

        SharedPreferences prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        monsterInformation = prefs.getInt("monster", -1);
        questId = prefs.getInt("idQuest", -1);

        queteDAO = new QueteDAO(context);
        quete = queteDAO.getById(questId);

        enemy = new Joueur();
        enemy =enemy.createMonster2(monsterInformation);

        String[] separeted = numberEnemyvie.getText().toString().split("/");
        String t = numberEnemyvie.getText().toString();
        enemy.setVie(Integer.valueOf(separeted[0].replaceAll("\\s+","")));

        Joueur.setMonEquipementList(monEquipementDAO.getAllEquipement(2));
        Joueur.adaptEquip();
    }

    public void spell(MonSort monSort)
    {
        Sort sort = monSort.getSort();
        //ATTAQUE
        if(sort.getType() == 1)
        {
            int coutMana = sort.getMana();

            if(coutMana <= Joueur.getMana())
            {
                Joueur.setMana(Joueur.getMana() - coutMana);
                joueurMana.setProgress(Joueur.getMana() * 100 / Joueur.getMana_max());
                pointManaJoueur.setText(Joueur.getMana() + " / " + Joueur.getMana_max());

                enemy.setVie(enemy.getVie() - sort.getGain());
                ennemieMana.setProgress(enemy.getVie() * 100 / enemy.getVie_max());

                int timer = showTextAfter("Vous avez attaqué ");

                if(enemy.getVie() <= 0)
                {
                   winGame();
                }
                else
                {
                    ennemieVie.setProgress(enemy.getVie() * 100 / enemy.getVie_max());
                    numberEnemyvie.setText(enemy.getVie() + "/" + enemy.getVie_max());
                    attaqueEnemy(timer);
                }
            }
            else
            {
                showText("Vous n'avez pas assez de réserve de mana");
            }
        }
        //HEAL
        else if(sort.getType() == 2)
        {

        }
        //BOOST
        else if(sort.getType() == 3)
        {

        }
    }

    public boolean heal(MonEquipement objet)
    {

        if(Joueur.getVie_max() == Joueur.getVie())
        {
            showText("Vos PV sont déjà au maximum ");
            return false;
        }
        else
        {
            viewPager.setVisibility(ViewPager.GONE);
            int timer = showTextAfter("Vous avez gagné 10 PV ");

            if(Joueur.getVie_max() < Joueur.getVie() + 10)
            {
                Joueur.setVie(Joueur.getVie_max());
                joueurVie.setProgress(100);
                pointVieJoueur.setText(Joueur.getVie() + "/" + Joueur.getVie_max());
            }
            else{
                Joueur.setVie(Joueur.getVie() + 10);
                joueurVie.setProgress(Joueur.getVie() * 100 / Joueur.getVie_max());
                pointVieJoueur.setText(Joueur.getVie() + "/" + Joueur.getVie_max());
            }

            equipementDAO.delete(objet.getEquipement().getId());
            monEquipementDAO.delete(objet.getId());

            attaqueEnemy(timer);

            return true;


        }
    }

    public void attaque()
    {

        viewPager.setVisibility(ViewPager.GONE);
        int timer = showTextAfter("Vous avez attaqué ");
        enemy.setVie(enemy.getVie() - Joueur.getAttaque());

        if(enemy.getVie() <= 0)
        {
            winGame();
        }
        else
        {
            ennemieVie.setProgress(enemy.getVie() * 100 / enemy.getVie_max());
            numberEnemyvie.setText(enemy.getVie() + "/" + enemy.getVie_max());
            attaqueEnemy(timer);

        }
    }


    public void showText(String text)
    {
        Thread thread = new Thread() {
            int i;
            @Override
            public void run() {
                try {
                    for (i = 0; i < text.length(); i++) { // use your variable text.leght()
                        Thread.sleep(30);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                informations.setText(text.substring(0, i));
                            }
                        });
                    }
                }
                catch (InterruptedException e) {}
            }
        };
        thread.start();
    }

    public int showTextAfter(String text)
    {
        Thread thread = new Thread() {
            int i;
            @Override
            public void run() {
                try {
                    for (i = 0; i < text.length(); i++) { // use your variable text.leght()
                        Thread.sleep(30);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                informations.setText(text.substring(0, i));
                            }
                        });
                    }
                }
                catch (InterruptedException e) {}
            }
        };
        thread.start();
        return text.length() * 50 + 1500;
    }

    public void endurance()
    {
        Intent unIntent = new Intent(context, Menu.class);
        //unIntent.putExtra("idConcour", itemId);
        activity.startActivity(unIntent);
        activity.finish();
    }

    public void attaqueEnemy(int timer)
    {
        text1 = "Attaque ennemie vous perdez " + enemy.getAttaque() + " points de vie ";
        text2 = "A votre tours  ";
        text3 = "Vous avez perdu  ";

        text2duration = text1.length() * 30 + 1500;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                Joueur.setVie(Joueur.getVie() - enemy.getAttaque());
                joueurDAO.update(Joueur);

                Thread thread = new Thread() {
                    int i;
                    @Override
                    public void run() {
                        try {
                            for (i = 0; i < text1.length(); i++) { // use your variable text.leght()
                                Thread.sleep(30);
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        informations.setText(text1.substring(0, i));

                                    }
                                });
                            }
                        }
                        catch (InterruptedException e) {}
                    }
                };
                thread.start();

                if(Joueur.getVie() <= 0)
                {
                    loseGame();
                }

                else
                {
                    joueurVie.setProgress(Joueur.getVie() * 100 / Joueur.getVie_max());
                    pointVieJoueur.setText(Joueur.getVie() + "/" + Joueur.getVie_max());

                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        public void run() {
                            Thread thread = new Thread() {
                                int i;
                                @Override
                                public void run() {
                                    try {
                                        for (i = 0; i < text2.length(); i++) { // use your variable text.leght()
                                            Thread.sleep(30);
                                            activity.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    informations.setText(text2.substring(0, i));
                                                }
                                            });
                                        }
                                    }
                                    catch (InterruptedException e) {}
                                }
                            };
                            thread.start();
                            viewPager.setVisibility(ViewPager.VISIBLE);
                        }
                    }, text2duration);
                }
            }
        }, timer);
    }

    public void winGame()
    {
        ennemieVie.setProgress(0);
        numberEnemyvie.setText("0/" + enemy.getVie_max());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            public void run() {
                showText("Vous avez tué votre ennemie vous avez gagné ... ");
                int oldNiveau = Joueur.getNiveau();
                int oldexperienceJoueur = Joueur.getEndurance();
                Joueur.setexperience(oldexperienceJoueur + enemy.getEndurance());
                Joueur.setArgent(Joueur.getArgent() + quete.getArgent());
                joueurDAO.update(Joueur);
                if(quete.getNombre() > 0)
                {
                    quete.setNombre(quete.getNombre() - 1);
                    queteDAO.update(quete);
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable()
                {
                    public void run() {
                        //atk.setEnabled(false);
                        if(oldNiveau != Joueur.getNiveau())
                        {
                                    FragmentManager fm = support;
                                    PopUpNiveauUp popUpNiveauUp = PopUpNiveauUp.newInstance("Titre");
                                    popUpNiveauUp.show(fm, "e_fragment_niveau_up");
                        }
                        else
                        {
                            endurance();
                        }
                    }
                }, 4000);
            }
        }, 3000);
    }

    public void loseGame()
    {
        joueurVie.setProgress(0);
        pointVieJoueur.setText("0/" + Joueur.getVie_max());

        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            public void run() {
                Thread thread = new Thread() {
                    int i;
                    @Override
                    public void run() {
                        try {
                            for (i = 0; i < text2.length(); i++) { // use your variable text.leght()
                                Thread.sleep(30);
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Joueur.setVie(Joueur.getVie_max());
                                        joueurDAO.update(Joueur);
                                        informations.setText(text3.substring(0, i));
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                endurance();
                                            }
                                        }, 2000);
                                    }
                                });
                            }
                        }
                        catch (InterruptedException e) {}
                    }
                };
                thread.start();
            }
        }, text2duration);
    }
}
