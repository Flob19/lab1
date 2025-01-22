//Ett interface är en slags "kontrakt" som specificerar vilka metoder en klass måste implementera,
// utan att ange hur de metoderna ska fungera. Det är som en mall – det säger vad som ska göras, men inte hur.

public interface Movable {
    void move();
    void turnLeft();
    void turnRight();
}

// Här säger vi att alla klasser som implementerar detta interface måste definiera
// (implementera) dessa tre metoder: move(), turnLeft(), och turnRight().
//Implementationen lämnas till de klasser som implementerar interfacet.