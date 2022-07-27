package soottocfg.ast.Absyn; // Java Package generated by the BNF Converter.

public class Mth2 extends MethodDecl {
  public final DeclaratorName declaratorname_;
  public final ListParameter listparameter_;
  public final String string_;
  public Mth2(DeclaratorName p1, ListParameter p2, String p3) { declaratorname_ = p1; listparameter_ = p2; string_ = p3; }

  public <R,A> R accept(soottocfg.ast.Absyn.MethodDecl.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof soottocfg.ast.Absyn.Mth2) {
      soottocfg.ast.Absyn.Mth2 x = (soottocfg.ast.Absyn.Mth2)o;
      return this.declaratorname_.equals(x.declaratorname_) && this.listparameter_.equals(x.listparameter_) && this.string_.equals(x.string_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(37*(this.declaratorname_.hashCode())+this.listparameter_.hashCode())+this.string_.hashCode();
  }


}
