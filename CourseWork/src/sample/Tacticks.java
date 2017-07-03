package sample;

public enum Tacticks {Centre(1),Snake(2), Around(3),Limitation(4),Mirroring(5);
    private int selection;

    Tacticks(int selection) {
        this.selection = selection;
    }

    public static Tacticks getInstance(int selection) {
        switch(selection) {
            case 1:
                return Centre;
            case 2:
                return Snake;
            case 3:
                return Around;
            case 4:
                return Limitation;
            case 5:
                return Mirroring;
            default:
                return Centre;

        }
    }

}