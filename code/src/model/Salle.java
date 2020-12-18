package model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.util.*;

public class Salle {
    private int num;
    private ArrayList<Carte> pioche;
    private Joueur joueur;
    private ObservableList<Monstre> monstres;
    int nbMonstre;
    private Boolean clean = true;
    private Bonus typeBonus;
    private int tour=1;

    public Salle(Joueur j, int n) {
        joueur = j;
        num = n;
        pioche = new ArrayList<>();
        monstres = new ObservableList<Monstre>(){
            public void addListener(InvalidationListener invalidationListener) { }
            public void removeListener(InvalidationListener invalidationListener) { }
            public int size() { return 0; }
            public boolean isEmpty() { return false; }
            public boolean contains(Object o) { return false; }
            public Iterator<Monstre> iterator() { return null; }
            public Object[] toArray() { return new Object[0]; }
            public <T> T[] toArray(T[] ts) { return null; }
            public boolean add(Monstre monstre) { return false; }

            public boolean remove(Object o) {
                return false;
            }

            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            public boolean addAll(Collection<? extends Monstre> collection) {
                return false;
            }

            public boolean addAll(int i, Collection<? extends Monstre> collection) {
                return false;
            }

            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            public void clear() {

            }

            public Monstre get(int i) {
                return null;
            }

            public Monstre set(int i, Monstre monstre) {
                return null;
            }

            public void add(int i, Monstre monstre) {

            }

            public Monstre remove(int i) {
                return null;
            }

            public int indexOf(Object o) {
                return 0;
            }

            public int lastIndexOf(Object o) {
                return 0;
            }

            public ListIterator<Monstre> listIterator() {
                return null;
            }

            public ListIterator<Monstre> listIterator(int i) {
                return null;
            }

            public List<Monstre> subList(int i, int i1) {
                return null;
            }

            public void addListener(ListChangeListener<? super Monstre> listChangeListener) {

            }

            public void removeListener(ListChangeListener<? super Monstre> listChangeListener) {

            }

            public boolean addAll(Monstre... monstres) {
                return false;
            }

            public boolean setAll(Monstre... monstres) {
                return false;
            }

            public boolean setAll(Collection<? extends Monstre> collection) {
                return false;
            }

            public boolean removeAll(Monstre... monstres) {
                return false;
            }

            public boolean retainAll(Monstre... monstres) {
                return false;
            }

            public void remove(int i, int i1) {

            }
        };
    }

    public int getNum() { return num; }

    public ArrayList<Carte> getPioche() { return pioche; }

    public ObservableList<Monstre> getMonstres() { return monstres; }

    public void setMonstre(Monstre m) { monstres.add(m); }

    public int getNbMonstre() {
        for ( Monstre m : monstres ) {
            nbMonstre++;
        }
        return nbMonstre;
    }

    public int getTour() { return tour; }

    public void setTour(int tour) { this.tour = tour; }

    private Boolean isClean() { return clean; }

    public void Victoire() {
        for ( Monstre m : monstres ) {
            if(m.getPdv() != 0)
                clean = false;
        }
        if(isClean())
            changerSalle(typeBonus);
    }

    public void changerSalle(Bonus b) {
        num++;
        new Salle(joueur, num);
        joueur.setSalle(num);
        joueur.renforcer(b,this);
    }

    public void setBonus(Bonus b) { typeBonus = b; }

    public boolean contientBoss(){
        ObservableList<Monstre> m=this.getMonstres();
        for (int i=0;i<m.size();i++){
            if(m.get(i).isBoss()){
                return true;
            }
        }
        return false;
    }
}
