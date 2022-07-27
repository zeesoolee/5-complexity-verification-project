package soottocfg.ast.Absyn; // Java Package generated by the BNF Converter.

public abstract class HeapStm implements java.io.Serializable {
  public abstract <R,A> R accept(HeapStm.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(soottocfg.ast.Absyn.PullSt p, A arg);
    public R visit(soottocfg.ast.Absyn.PushSt p, A arg);
    public R visit(soottocfg.ast.Absyn.HavocSt p, A arg);

  }

}
