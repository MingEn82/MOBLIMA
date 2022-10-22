package Entities;

import java.util.ArrayList;

public class Cinema {
    private CinemaType cinemaType;
    private ArrayList<Showing> showings;
    private String cinemaName;
    private String cinemaNumber;
    private int aisleIndex;

    public Cinema(CinemaType cinemaType, ArrayList<Showing> showings, String cinemaName, String cinemaNumber, int aisleIndex) {
        this.cinemaType = cinemaType;
        this.showings = showings;
        this.cinemaName = cinemaName;
        this.cinemaNumber = cinemaNumber;
        this.aisleIndex = aisleIndex;
    }

    public void print() {
        System.out.println("--------- Cinema Details ---------");
        System.out.println("Cinema Type: " + cinemaType.toString());
        System.out.println("Cinema Name: " + cinemaName);
        System.out.println("Cinema Number: " + cinemaNumber);
        System.err.println("");
        System.out.println("--------- Showings Details ---------");
        if (showings.size() > 0) {
            for (Showing showing : showings) {
                showing.print(aisleIndex);
            }
        } else {
            System.out.println("No showings");
            System.out.println("");
        }
        
    }

    public String getCinemaType() {
        return this.cinemaType.toString();
    }

    public String getCinemaName() {
        return this.cinemaName;
    }

    public String getCinemaNumber() {
        return this.cinemaNumber;
    }

    public int getAisleIndex() {
        return this.aisleIndex;
    }

    public ArrayList<Showing> getShowings() {
        return this.showings;
    }

    public void setShowings(ArrayList<Showing> showings) {
        this.showings = showings;
    }

    public void addShowing(Showing s) {
        showings.add(s);
    }

    public void removeShowing(Showing s) {
        int idx = -1;
        int i = 0;
        for (Showing showing : showings) {
            if (showing.getStartDate().compareTo(s.getStartDate()) == 0 && showing.getMovieTitle().equals(s.getMovieTitle())) {
                idx = i;
                break;
            }
            i++;
        }
        
        if (idx > -1) {
            showings.remove(idx);
        }
    }
}
