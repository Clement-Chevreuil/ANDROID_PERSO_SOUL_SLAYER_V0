package com.example.android_perso_soul_slayer_v0.VISUAL_ACTIVITY_3_FIGHT;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
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
    TextView informations, ennemieNom, joueurNom, pointVieEnnemie, pointVieJoueur, pointManaEnnemie, pointManaJoueur, pointEnduranceJoueur, pointEnduranceEnnemie;
    ProgressBar ennemieVie, joueurVie;
    JoueurDAO joueurDAO;
    Joueur joueur, ennemie;
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

    public CombatActions(Activity activity, Context context, FragmentManager support) {
        this.activity = activity;
        this.context = context;

        SharedPreferences prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE);

        //JOUEUR
        joueurDAO = new JoueurDAO(context);
        joueur = joueurDAO.getMonJoueur();
        monEquipementDAO = new MonEquipementDAO(context);
        equipementDAO = new EquipementDAO(context);
        joueur.setMonEquipementList(monEquipementDAO.getAllEquipement());
        joueur.adaptEquip();

        joueurVie = activity.findViewById(R.id.VieJoueur);
        pointVieJoueur = activity.findViewById(R.id.PointVieJoueur);
        pointEnduranceJoueur = activity.findViewById(R.id.PointEnduranceJoueur);
        pointManaJoueur = activity.findViewById(R.id.PointManaJoueur);
        joueurNom = activity.findViewById(R.id.NomJoueur);

        //ENNEMIE
        monsterInformation = prefs.getInt("monster", -1);
        ennemie = new Joueur();
        ennemie = ennemie.createMonster2(monsterInformation);

        pointEnduranceEnnemie = activity.findViewById(R.id.PointEnduranceEnnemie);
        pointVieEnnemie = activity.findViewById(R.id.PointVieEnnemie);
        pointManaEnnemie = activity.findViewById(R.id.PointManaEnnemie);
        ennemieVie = activity.findViewById(R.id.VieEnnemie);
        ennemieNom = activity.findViewById(R.id.NomEnnemie);

        //QUETE
        questId = prefs.getInt("idQuest", -1);
        queteDAO = new QueteDAO(context);
        quete = queteDAO.getById(questId);

        //AUTRE
        informations = activity.findViewById(R.id.Informations);
        viewPager = activity.findViewById(R.id.view_pager);
        punch = activity.findViewById(R.id.Frappe);
        list = activity.findViewById(R.id.ListObjet);
        this.support = support;

        String[] separeted = pointVieEnnemie.getText().toString().split("/");
        String t = pointVieEnnemie.getText().toString();
        ennemie.setVie(Integer.valueOf(separeted[0].replaceAll("\\s+", "")));
    }

    public void spell(MonSort monSort) {
        Sort sort = monSort.getSort();
        //ATTAQUE
        if (sort.getType() == 1) {
            int coutMana = sort.getMana();

            if (coutMana <= joueur.getMana()) {
                joueur.setMana(joueur.getMana() - coutMana);
                pointManaJoueur.setText(joueur.getMana() + " / " + joueur.getMana_max());

                ennemie.setVie(ennemie.getVie() - sort.getGain());
                ennemieVie.setProgress(ennemie.getVie() * 100 / ennemie.getVie_max());

                int timer = showTextAfter("Vous avez attaqué ");

                if (ennemie.getVie() <= 0) {
                    gagner();
                } else {
                    ennemieVie.setProgress(ennemie.getVie() * 100 / ennemie.getVie_max());
                    pointVieEnnemie.setText(ennemie.getVie() + "/" + ennemie.getVie_max());
                    tourEnnemie(timer);
                }
            } else {
                showText("Vous n'avez pas assez de réserve de mana");
            }
        }
        //HEAL
        else if (sort.getType() == 2) {

        }
        //BOOST
        else if (sort.getType() == 3) {

        }
    }

    public boolean heal(MonEquipement objet) {

        if (joueur.getVie_max() == joueur.getVie()) {
            showText("Vos PV sont déjà au maximum ");
            return false;
        } else {
            viewPager.setVisibility(ViewPager.GONE);
            int timer = showTextAfter("Vous avez gagné 10 PV ");

            if (joueur.getVie_max() < joueur.getVie() + 10) {
                joueur.setVie(joueur.getVie_max());
                joueurVie.setProgress(100);
                pointVieJoueur.setText(joueur.getVie() + "/" + joueur.getVie_max());
            } else {
                joueur.setVie(joueur.getVie() + 10);
                joueurVie.setProgress(joueur.getVie() * 100 / joueur.getVie_max());
                pointVieJoueur.setText(joueur.getVie() + "/" + joueur.getVie_max());
            }

            equipementDAO.delete(objet.getEquipement().getId());
            monEquipementDAO.delete(objet.getId());

            tourEnnemie(timer);

            return true;


        }
    }

    public void attaque() {
        int timer = 100;
        if (joueur.getEndurance() != 0) {
            viewPager.setVisibility(ViewPager.GONE);
            timer = showTextAfter("Vous avez attaqué ");
            ennemie.setVie(ennemie.getVie() - joueur.getAttaque());
            joueur.setEndurance(joueur.getEndurance() - 1);
            pointEnduranceJoueur.setText(joueur.getEndurance() + " / " + joueur.getEndurance_max() + " END");

            if (ennemie.getVie() <= 0) {
                gagner();
            } else {
                ennemieVie.setProgress(ennemie.getVie() * 100 / ennemie.getVie_max());
                pointVieEnnemie.setText(ennemie.getVie() + "/" + ennemie.getVie_max());
                tourEnnemie(timer);
            }
        } else {
            timer = showTextAfter("Vous devez vous reposez un peu ");
            joueur.setEndurance(joueur.getEndurance() + 1);
            pointEnduranceJoueur.setText(joueur.getEndurance() + " / " + joueur.getEndurance_max() + " END");
            tourEnnemie(timer);
        }
    }

    public void tourEnnemie(int timer) {
        if (ennemie.getEndurance() <= 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    attaqueEnemmie();
                }
            }, timer);
        } else {
            String text = "L'ennemie ne peut pas attaquer, c'est le moment de lui porter un coup";
            showText(text);
            ennemie.setEndurance(ennemie.getEndurance() + 1);
        }
    }

    public void attaqueEnemmie() {
        text1 = "Attaque ennemie vous perdez " + ennemie.getAttaque() + " points de vie ";
        text2 = "A votre tours  ";
        text3 = "Vous avez perdu  ";
        text2duration = text1.length() * 30 + 1500;

        joueur.setVie(joueur.getVie() - ennemie.getAttaque());
        joueurDAO.update(joueur);
        showText(text1);
        if (joueur.getVie() <= 0) {
            perdre();
        } else {
            joueurVie.setProgress(joueur.getVie() * 100 / joueur.getVie_max());
            pointVieJoueur.setText(joueur.getVie() + "/" + joueur.getVie_max());

            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                public void run() {
                    showText(text2);
                    viewPager.setVisibility(ViewPager.VISIBLE);
                }
            }, text2duration);
        }
    }

    public void showText(String text) {
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
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();
    }

    public int showTextAfter(String text) {
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
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();
        return text.length() * 50 + 1500;
    }

    public void gagner() {
        ennemieVie.setProgress(0);
        pointVieEnnemie.setText("0/" + ennemie.getVie_max());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            public void run() {
                showText("Vous avez tué votre ennemie vous avez gagné ... ");
                int oldNiveau = joueur.getNiveau();
                int oldexperienceJoueur = joueur.getEndurance();
                joueur.setexperience(oldexperienceJoueur + ennemie.getEndurance());
                joueur.setArgent(joueur.getArgent() + quete.getArgent());
                joueurDAO.update(joueur);
                if (quete.getNombre() > 0) {
                    quete.setNombre(quete.getNombre() - 1);
                    queteDAO.update(quete);
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        //atk.setEnabled(false);
                        if (oldNiveau != joueur.getNiveau()) {
                            FragmentManager fm = support;
                            PopUpMonteeNiveau popUpNiveauUp = PopUpMonteeNiveau.newInstance("Titre");
                            popUpNiveauUp.show(fm, "e_fragment_niveau_up");
                        } else {
                            fin();
                        }
                    }
                }, 4000);
            }
        }, 3000);
    }

    public void perdre() {
        joueurVie.setProgress(0);
        pointVieJoueur.setText("0/" + joueur.getVie_max());

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
                                        joueur.setVie(joueur.getVie_max());
                                        joueurDAO.update(joueur);
                                        informations.setText(text3.substring(0, i));
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                fin();
                                            }
                                        }, 2000);
                                    }
                                });
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                };
                thread.start();
            }
        }, text2duration);
    }

    public void fin() {
        Intent unIntent = new Intent(context, Menu.class);
        //unIntent.putExtra("idConcour", itemId);
        activity.startActivity(unIntent);
        activity.finish();
    }
}
