package soottocfg.ast.Absyn; // Java Package generated by the BNF Converter.

public class IBody extends MethodBody {
  public IBody() { }

  public <R,A> R accept(soottocfg.ast.Absyn.MethodBody.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof soottocfg.ast.Absyn.IBody) {
      return true;
    }
    return false;
  }

  public int hashCode() {
    return 37;
  }


}
