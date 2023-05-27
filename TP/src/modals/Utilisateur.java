package modals;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import controlleurs.Alerts;
import java.util.Iterator;;

public class Utilisateur implements Serializable {

    @Override
    public String toString() {
        return "----------------------------\nUtilisateur : pseudo=" + pseudo + "\n->planning :\n" + planning
                + "\n->dureeMin=\n" + dureeMin + "\n->tacheMin=\n"
                + tacheMin + "\n->tachesNotPlanned= \n" + tachesNotPlanned + "\n->myCategories=\n" + myCategories
                + "\n->projets=\n"
                + projets + "]\n----------------------------";
    }

    public Utilisateur(String pseudo, Long dureeMin, int tacheMin) {
        this.pseudo = pseudo;
        this.dureeMin = dureeMin;
        this.tacheMin = tacheMin;
        badge.put(Badge.GOOD, 0);
        badge.put(Badge.VeryGOOD, 0);
        badge.put(Badge.EXCELLENT, 0);
        this.nb_felicitation = 0; // initialement aucune félicitation n'est affichée
    }

    public Utilisateur() {
    }

    private String pseudo;
    private ArrayList<Planning> planning = new ArrayList<>();
    private Long dureeMin; // durée minimale d'un créneau
    private int tacheMin; // nombre minimal de tâche/jour pour être récompensé
    private Integer nb_felicitation; // contient le nombre de félicitations obtenues
    private ArrayList<Tache> tachesNotPlanned = new ArrayList<>(); // contains all the tasks li dkhlhom user w mazel
                                                                   // maplanifahomch
    private ArrayList<Categorie> myCategories = new ArrayList<>();
    private ArrayList<Projet> projets = new ArrayList<>(); // la liste qui contient tous les projets à planifier

    private Map<LocalDate, Integer> tachesCompleted = new HashMap<>(); // une map contenant le nombre de tâches
                                                                       // complétées pour chaque date
    private Map<Badge, Integer> badge = new HashMap<>(); // map contenant pour chaque badge le nombre de fois ou on l'a
                                                         // obtenu

    public void attribuerBadge(int nbTacheComp) {
        if (nbTacheComp == tacheMin) {
            this.nb_felicitation++;
            Alerts.Feliciter();
        }
        if (nb_felicitation % 5 == 0 && nb_felicitation != 0) {
            badge.put(Badge.GOOD, badge.get(Badge.GOOD) + 1);
            Alerts.FeliciterGood();
        }
        if (badge.get(Badge.GOOD) % 3 == 0 && badge.get(Badge.GOOD) != 0) {
            // le nombre be dasge GOOD est multiple de 3 => badge VerryGOOD
            badge.put(Badge.VeryGOOD, badge.get(Badge.VeryGOOD) + 1);
            Alerts.FeliciterGood();
        }
        if (badge.get(Badge.VeryGOOD) % 3 == 0 && badge.get(Badge.VeryGOOD) != 0) {
            badge.put(Badge.EXCELLENT, badge.get(Badge.EXCELLENT) + 1);
            Alerts.FeliciterEXCELLENT();
        }

    }

    public ArrayList<Tache> plannedTaches() {
        /*
         * retourne une liste contenant toutes les tâches plannifiées de tous les
         * plannings
         */
        ArrayList<Tache> tachesList = new ArrayList<>();
        for (Planning element : planning) {
            for (Tache cle : element.getTachesPlanned().keySet()) {
                tachesList.add(cle);
            }
        }
        return tachesList;
    }

    public Planning getTachePlanning(Tache tache) {
        /*
         * retourne une liste contenant toutes les tâches plannifiées de tous les
         * plannings
         */
        for (Planning element : planning) {
            for (Tache cle : element.getTachesPlanned().keySet()) {
                if (tache == cle)
                    return element;
            }
        }
        return null;
    }

    public Integer getNbTacheTotal() {
        /* retourne le nombre total de tâches planifiées */
        Integer nb = 0;
        for (Planning element : planning) {
            nb = nb + element.getTachesPlanned().size();
        }
        return nb;
    }

    public Integer getNbTacheCompleted() {
        Integer nb = 0;
        for (LocalDate cle : tachesCompleted.keySet()) {
            nb = nb + tachesCompleted.get(cle);
        }
        return nb;
    }

    public boolean isPeriodAvailable(PeriodMe period) {
        for (Planning _planning : planning) {
            if (_planning.getPeriod().overlaps(period)) {
                return false; // Period is not available
            }
        }
        return true; // Period is available
    }

    public ArrayList<Tache> getAllPlannedDay(LocalDate d) {
        ArrayList<Tache> a = new ArrayList<>();
        for (Planning _planning : planning) {
            if (_planning.getPeriod().containsDate(d)) {
                a.addAll(_planning.getTachesForDay(d));
            }
        }
        return a; // Period is available
    }

    public void deletePlanning(Planning planningToDelete) {
        planning.remove(planningToDelete);
    }

    public void addPlanning(Planning newPlanning) {
        planning.add(newPlanning);
    }

    public Planning isPeriodAvailable2(PeriodMe period) {
        Planning p = null;
        for (Planning _planning : planning) {
            if (_planning.getPeriod().overlaps2(period)) {
                p = _planning; // Period is not available
            }
        }
        return p; // Period is available
    }

    private int isDate(LocalDate d) {
        // teste si la date "d" est déja dans un des plannings
        int index = -1;
        ArrayList<Planning> myPlannings = planning;
        if (myPlannings != null) {
            ListIterator<Planning> iterator = myPlannings.listIterator();
            while (iterator.hasNext()) {
                Planning currentPlanning = iterator.next();
                if (currentPlanning.getPeriod().containsDate(d) || (currentPlanning.getPeriod().isTacheSimple()
                        && currentPlanning.getPeriod().getStartDate().isEqual(d))) {
                    index = myPlannings.indexOf(currentPlanning);
                    break;
                }
            }
        }
        return index;
    }

    private boolean availableSlot(LocalDate d, Creneau c, int indexPlanning) {
        // retourne vrai si pour une date de planning donnée le créneau est libre
        if (planning.get(indexPlanning).getPeriod().containsDateAndCreneau(d, c) != -1) {
            return true;
        }
        return false;
    }

    private boolean creneauOccupe(Creneau c, int indexPlanning) {
        // retrourne vrai si le créneau est occupé
        return planning.get(indexPlanning).containsCreneauValue(c);
    }

    private boolean creneauNotExist(LocalDate d, Creneau c, int indexPlanning) {
        // retourne vrai si le créneau n'existe
        return (!availableSlot(d, c, indexPlanning) && !creneauOccupe(c, indexPlanning));
    }

    public boolean planNewTaskSimple(Tache t, LocalDate d, Creneau c) {
        // planifier manuellement une tâche simple
        if (t instanceof Simple) {
            if (isDate(d) == -1) { // la date n'est dans aucun planning
                PeriodMe periodMe = new PeriodMe(d, d);
                Planning _planning = new Planning(periodMe);
                t.setEtat(Etat.INPROGRESS);
                t.setDate(d);
                _planning.addSingleTask(t, c);
                this.planning.add(_planning);
                return true;// planification is done
            } else {// un planning contient cette date
                if (creneauOccupe(c, isDate(d))) {
                    // 2eme cas : le créneau est occupé, planification impossible
                    return false;
                } else {
                    if (availableSlot(d, c, isDate(d))) {
                        // 3eme cas : le créneau est libre
                        /* il faut vérifier si le créneau est divisible ou non puis planifier */
                        if (c.isDivisible(t.getDuree())) {
                            LocalTime firstSlotfin = c.getHeureDebut().plusMinutes(t.getDuree());
                            LocalTime endSLocalTime = c.getHeureDebut().plusMinutes(t.getDuree());
                            Creneau firstSlot = new Creneau(c.getHeureDebut(), firstSlotfin, this.dureeMin, false);
                            Creneau creneau2 = new Creneau(endSLocalTime, c.getHeureFin(), this.dureeMin, false);
                            t.setEtat(Etat.INPROGRESS);
                            t.setDate(d);
                            planning.get(isDate(d)).addSingleTask(t, firstSlot);
                            planning.get(isDate(d)).getPeriod().removeSlot(d, c);
                            planning.get(isDate(d)).getPeriod().addSlot(d, creneau2);
                            return true;
                        } else {
                            /*
                             * s'il n'est pas divisible je l'enlève de la liste des créneaux libres et je
                             * planifie
                             */
                            t.setEtat(Etat.INPROGRESS);
                            t.setDate(d);
                            planning.get(isDate(d)).addSingleTask(t, c);
                            planning.get(isDate(d)).getPeriod().removeSlot(d, c);
                            return true;
                        }
                    } else {
                        if (creneauNotExist(d, c, isDate(d))) {
                            // 1er cas : le creneau n'est ni occupé ni libre, ajouter la tache
                            t.setEtat(Etat.INPROGRESS);
                            t.setDate(d);
                            planning.get(isDate(d)).addSingleTask(t, c);
                            return true;
                        }
                    }
                }
            }
        } else {
            return false;
        }
        return false;
    }

    public Planning planAuto(ArrayList<Tache> listToPlan, Planning planning2, boolean isExtend) {
        Planning planning = new Planning(planning2.getPeriod());
        ArrayList<Tache> tachSche = new ArrayList<>();
        ArrayList<Tache> listeTachesUnscheduled = new ArrayList<>();
        planning.getPeriod().sortCreneau();// ordonner les créneaux
        listToPlan = sortArrayListTaches(listToPlan);// ordonner les tâches
        LocalDate lastLocalDate = planning.getPeriod().getEndDate();
        Iterator iteratorTaches = listToPlan.iterator();
        while (iteratorTaches.hasNext()) {
            Tache tache = (Tache) iteratorTaches.next();
            boolean tacheScheduled = false;
            LocalDate startDayDate = planning.getPeriod().getStartDate().minusDays(1);
            while (startDayDate.isBefore(planning.getPeriod().getEndDate()) && tache.getEtat() == Etat.UNSCHEDULED) {
                startDayDate = startDayDate.plusDays(1);
                ArrayList<Creneau> listAvailableSlots = planning.getPeriod().returnCreneauOfTheDay(startDayDate);
                if (listAvailableSlots == null) {
                    tacheScheduled = true;
                    break;
                } else {
                    Iterator<Creneau> iteratorlistAvailableSlots = listAvailableSlots.iterator();
                    while (iteratorlistAvailableSlots.hasNext()) {
                        Creneau availableSlot = iteratorlistAvailableSlots.next();
                        if ((tache.getDate_limite().isAfter(startDayDate)) ||
                                (tache.getDate_limite().isEqual(startDayDate))) {
                            if (tache instanceof Decomposable) {
                                if (tache.getDuree() > availableSlot.getDuree()) {
                                    Decomposable taskDecomposable = (Decomposable) tache;
                                    Simple ssTask = new Simple(
                                            tache.getNom() + (taskDecomposable.getSubTaches().size() + 1),
                                            availableSlot.getDuree(), tache.getDate_limite(), tache.getPriorite(),
                                            tache.getCategorie(), 0);
                                    ssTask.setDate(startDayDate);
                                    ssTask.setEtat(Etat.INPROGRESS);
                                    taskDecomposable.addTosSubTaches(ssTask);
                                    planning.addSingleTask(ssTask, availableSlot);
                                    taskDecomposable.setDuree(taskDecomposable.getDuree() - ssTask.getDuree());
                                    planning.getPeriod().removeSlot(startDayDate, availableSlot);
                                    tache = taskDecomposable;
                                    break;
                                } else {// pas de décomposition de la tâche
                                    Decomposable taskDecomposable = (Decomposable) tache;
                                    if (availableSlot.isDivisible(taskDecomposable.getDuree())) {// si le créneau est
                                                                                                 // divisible
                                        LocalTime firstSlotfin = availableSlot.getHeureDebut()
                                                .plusMinutes(tache.getDuree());
                                        LocalTime endSLocalTime = availableSlot.getHeureDebut()
                                                .plusMinutes(tache.getDuree());
                                        Creneau firstSlot = new Creneau(availableSlot.getHeureDebut(), firstSlotfin,
                                                this.dureeMin, false);
                                        Creneau creneau2 = new Creneau(endSLocalTime, availableSlot.getHeureFin(),
                                                this.dureeMin, false);
                                        Simple ssTask = new Simple(
                                                tache.getNom() + (taskDecomposable.getSubTaches().size() + 1),
                                                tache.getDuree(), tache.getDate_limite(), tache.getPriorite(),
                                                tache.getCategorie(), 0);
                                        ssTask.setDate(startDayDate);
                                        ssTask.setEtat(Etat.INPROGRESS);
                                        taskDecomposable.addTosSubTaches(ssTask);
                                        planning.addSingleTask(ssTask, firstSlot);
                                        planning.getPeriod().removeSlot(startDayDate, availableSlot);
                                        planning.getPeriod().addSlot(startDayDate, creneau2);
                                        iteratorTaches.remove();
                                        tacheScheduled = true;
                                        break;
                                    } else {
                                        Simple ssTask = new Simple(
                                                tache.getNom() + (taskDecomposable.getSubTaches().size() + 1),
                                                tache.getDuree(), tache.getDate_limite(), tache.getPriorite(),
                                                tache.getCategorie(), 0);
                                        ssTask.setDate(startDayDate);
                                        ssTask.setEtat(Etat.INPROGRESS);
                                        taskDecomposable.addTosSubTaches(ssTask);
                                        planning.addSingleTask(ssTask, availableSlot);
                                        planning.getPeriod().removeSlot(startDayDate, availableSlot);
                                        iteratorTaches.remove();
                                        tacheScheduled = true;
                                        break;
                                    }
                                }
                            } else {
                                if (tache instanceof Simple) {

                                    if (availableSlot.isDivisible(tache.getDuree())) {
                                        // si le créneau est divisible
                                        LocalTime firstSlotfin = availableSlot.getHeureDebut()
                                                .plusMinutes(tache.getDuree());
                                        LocalTime endSLocalTime = availableSlot.getHeureDebut()
                                                .plusMinutes(tache.getDuree());
                                        Creneau firstSlot = new Creneau(availableSlot.getHeureDebut(), firstSlotfin,
                                                this.dureeMin, false);
                                        Creneau creneau2 = new Creneau(endSLocalTime, availableSlot.getHeureFin(),
                                                this.dureeMin, false);
                                        tache.setEtat(Etat.INPROGRESS);
                                        tache.setDate(startDayDate);
                                        planning.addSingleTask(tache, firstSlot);
                                        planning.getPeriod().removeSlot(startDayDate, availableSlot);
                                        planning.getPeriod().addSlot(startDayDate, creneau2);
                                        iteratorTaches.remove();
                                        break;
                                    } else {
                                        tache.setEtat(Etat.INPROGRESS);
                                        tache.setDate(startDayDate);
                                        planning.addSingleTask(tache, availableSlot);
                                        planning.getPeriod().removeSlot(startDayDate, availableSlot);
                                        iteratorTaches.remove();
                                        break;
                                    }

                                }

                            }
                        } else {
                            System.out.println("Le deadline de cette tache a été dépassé");
                            break;
                        }
                    }
                }
                if (tache.getEtat() == Etat.INPROGRESS) {
                    tachSche.add(tache);
                    lastLocalDate = startDayDate;
                    break; // Quitter la boucle si la tache a été programmée
                }
                if (tacheScheduled) {
                    break;
                }
            }
            if (!tacheScheduled) {
                continue;
            }
        }
        for (Tache tache : listToPlan) {
            if (tache.getEtat() == Etat.UNSCHEDULED) {
                listeTachesUnscheduled.add(tache);
            }
        }
        if (!listeTachesUnscheduled.isEmpty()) {
            if (isExtend) {
                // create the extended planning and plan it
                LocalTime time1 = LocalTime.of(9, 0);
                LocalTime time2 = LocalTime.of(14, 30);
                Creneau creneau = new Creneau(time1, time2, dureeMin, false);
                ArrayList<Creneau> mySlots = new ArrayList<>();
                mySlots.add(creneau);
                PeriodMe period = new PeriodMe(lastLocalDate.plusDays(1), lastLocalDate.plusDays(2));
                period.setSpecificAvailableSlot(mySlots);
                Planning newPlanning = new Planning(period);

                Planning new_ = planAuto(listeTachesUnscheduled, newPlanning, false);
                planning.getPeriod().setSpecificAvailableSlot(mySlots);

                planning.addTachesToPlanned(new_.getTachesPlanned());
                listeTachesUnscheduled.clear();
                System.out.println("\n\nPlanning proposé 1 " + planning);
                return planning;
            }

        }
        this.tachesNotPlanned.removeAll(tachSche);
        System.out.println("\n\n-----------\nVoici le plannng proposé : \n " + planning);
        return (planning);
    }

    public ArrayList<Tache> sortArrayListTaches(ArrayList<Tache> listToPlan) {
        // Sort the tasks by deadline date, deadline time, and priority
        listToPlan.sort(Comparator.comparing(Tache::getDate_limite)
                .thenComparing(Tache::getPriorite));
        return listToPlan;
    }

    public void addNewProject(Projet p) {
        projets.add(p);
    }

    public void deleteProject(Projet p) {
        projets.remove(p);
    }

    public boolean isColor(java.awt.Color c) {
        for (Categorie category : myCategories) {
            if (category.getCouleur().equals(c)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNameCategory(String n) {
        for (Categorie category : myCategories) {
            if (category.getNom().equals(n)) {
                return true;
            }
        }
        return false;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public ArrayList<Planning> getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning.add(planning);
    }

    public Long getDureeMin() {
        return dureeMin;
    }

    public void setDureeMin(Long dureeMin) {
        this.dureeMin = dureeMin;
    }

    public int getTacheMin() {
        return tacheMin;
    }

    public void setTacheMin(int tacheMin) {
        this.tacheMin = tacheMin;
    }

    public void setPlanning(ArrayList<Planning> planning) {
        this.planning = planning;
    }

    public ArrayList<Tache> getTachesNotPlanned() {
        return tachesNotPlanned;
    }

    public void addTaskToTachesNotPlanned(Tache task) {
        tachesNotPlanned.add(task);
    }

    public void removeTaskFromTachesNotPlanned(Tache task) {
        tachesNotPlanned.remove(task);
    }

    public void addNewCategory(Categorie c) {
        myCategories.add(c);
    }

    public void deleteCategory(Categorie c) {
        myCategories.remove(c);
    }

    public ArrayList<Categorie> getMyCategories() {
        return myCategories;
    }

    public ArrayList<Projet> getProjets() {
        return projets;
    }

    public void setProjets(ArrayList<Projet> projets) {
        this.projets = projets;
    }

    public Map<Badge, Integer> getBadge() {
        return badge;
    }

    public void setBadge(Map<Badge, Integer> badge) {
        this.badge = badge;
    }

    public int getGood() {
        return badge.get(Badge.GOOD);
    }

    public int getVeryGOOD() {
        return badge.get(Badge.VeryGOOD);
    }

    public int getEXCELLENT() {
        return badge.get(Badge.EXCELLENT);
    }

    public void setTachesCompleted(Map<LocalDate, Integer> tachesCompleted) {
        this.tachesCompleted = tachesCompleted;
    }

    public Map<LocalDate, Integer> getTachesCompleted() {
        return tachesCompleted;
    }

    public Integer getNb_felicitation() {
        return nb_felicitation;
    }

    public void setNb_felicitation(Integer nb_felicitation) {
        this.nb_felicitation = nb_felicitation;
    }

    public void setTachesNotPlanned(ArrayList<Tache> tachesNotPlanned) {
        this.tachesNotPlanned = tachesNotPlanned;
    }

    public void setMyCategories(ArrayList<Categorie> myCategories) {
        this.myCategories = myCategories;
    }

}
