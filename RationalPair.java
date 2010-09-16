public class RationalPair {
  public int a,b,c,d;
  
  public RationalPair(Rational r1, Rational r2) {
    a = r1.getNumerator();
    b = r1.getDenominator();
    c = r2.getNumerator();
    d = r2.getDenominator();
  }
}