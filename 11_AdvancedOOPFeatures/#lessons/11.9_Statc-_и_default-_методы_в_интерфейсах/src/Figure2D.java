public interface Figure2D {
    default Double getSquare() {  // Этот метод будет доступен во всех наследниках и его
        return 0.0;             // можно переопределять (но класс не обязан его реализовывать)
    }                          // см. класс Circle

    // Но если создаем статический метод, то его переопределить будет нельзя
    static Figure2D createFigure(Object data) {
        return null;
    }
}
