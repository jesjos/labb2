import java.util.regex.*;
import java.util.ArrayList;

class Rational{
  
  // Variables

  // numerator
  private int n;
  // denominator
  private int d;
  
  
  // Constructors
  public Rational() { 
    set(0);
  }
  
  public Rational(int a) {
    set(a);
  }
  
  public Rational(int a, int b) {
    set(a,b);
  }
  
  public Rational(Rational r) {
    set(r);
  }
  
  // Methods
  
  public int getNumerator() {
    return n;
  }
  
  public int getDenominator() {
    return d;
  }
  
  
  /*  set(a,b) sets the numerator and denominator. Checks that b != 0, that a and b haven't got a divisor larger than 1.
      Divides a and b with divisor if nessecary */
  
  public void set (int a, int b){
    
    if (b == 0) {
      System.err.println("Invalid denominator, cannot be 0");
      System.exit(1);
    }
    
    if (a == 0) { n = a; d = b; }
    
    else {
      
      // the denominator is always positive  
      int num,denom;
      denom = Math.abs(b);
      
      // if a*b is larger than 0, the number at hand will always be positive
      if (a > 0 && b > 0 || a < 0 && b < 0) {
        num = Math.abs(a);
      }
      
      else {
        num = a < 0 ? a : a*-1;
      }
      
      // Check that numbers cannot be divided further
      int divisor = sgd(Math.abs(a), Math.abs(b));
      
      if (divisor == 1) {
        n = num;
        d = denom;
      }
      else {
        n = num/divisor;
        d = denom/divisor;
      } 
    }
  }
  
  public void set (int a) {
    n = a;
    d = 1;
  }
  
  public void set (Rational r) {
    this.n = r.getNumerator();
    this.d = r.getDenominator();
  }
  
  
  // sgd calculates the biggest common denominator of two integers
  public static int sgd (int one, int two) {
    int r, m, n;
    
    // Makes sure that m is always the largest of the two input numbers.
    m = Math.max(one, two);
    n = Math.min(one, two);
    
    r = m % n;
    
    if (r == 0) {
        return n;
      }
    else {
        return sgd(n,r);
    }
  }
  
  public String toString () {
    return (Integer.toString(n) + "/" + Integer.toString(d));
  }
  
  
  /*  parse takes a string and attempts to construct a rational from it. 
      If input string is of the wrong format method returns null.
      Note: in our opinion it could have been OK to allow b = 0, since the set method would handle the error
      As the method is written now, strings where b = 0 will not be matched.
  */
  public static Rational parse(String s) {
    Pattern p = Pattern.compile("^(-?\\d+)(\\/(-?[1-9]{1}\\d*))?$");
    Matcher m = p.matcher(s);
      
    while (m.find()) {
      
      int a, b = 1;
      a = Integer.valueOf(m.group(1));
      
      if (m.group(3) != null) {
        b = Integer.valueOf(m.group(3));
      }
      return new Rational(a,b);
    }
    
    return null;
  }
  
  public Object clone () {
    Rational r = new Rational(this.getNumerator(), this.getDenominator());
    return (Object) r;
  }
  
  // If two rationals equal each other, their string representations should match
  public boolean equals (Rational r) {
    return r.toString().equals(this.toString()); 
  }
  
  //  Checks if the object at hand is less than the argument rational r.
  public boolean lessThan (Rational r) {
    int n1,n2;
    
    if (this.getDenominator() != r.getDenominator()) {
      n1 = this.getNumerator() * r.getDenominator();
      n2 = r.getNumerator() * this.getDenominator();
    }
    else {
      n1 = this.getNumerator();
      n2 = r.getNumerator();
    }
    
    return n1 < n2;
    
  }
  
  
  // Various arithmetic methods, helper class RationalPair used for easier access to numerators and denominators
  public Rational add (Rational r) {
    RationalPair pair = new RationalPair(this,r);
    
    return new Rational(pair.a*pair.d + pair.c*pair.b, pair.c*pair.d);
  }
  
  public Rational sub (Rational r) {
    RationalPair pair = new RationalPair(this,r);
    
    return new Rational(pair.a*pair.d - pair.c*pair.b, pair.c*pair.d);
  }
  
  public Rational mul (Rational r) {
    RationalPair pair = new RationalPair(this,r);
    
    return new Rational(pair.a*pair.c, pair.b*pair.d);
  }
  
  public Rational div (Rational r) {
    RationalPair pair = new RationalPair(this,r);
    
    return new Rational(pair.a*pair.d, pair.b*pair.c);
  }  
  
}