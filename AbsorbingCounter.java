import java.util.Random;

class AbsorbingCounter {
    long a;
    long b;
    long counter;
 public AbsorbingCounter(final long min, final long max) {
    a = min;
    b = max;
    if (a > b) {
    b = min;
    a = max;
    }
    else if (a < b) {
    a = min;
    b = max;
    }
    else if (a == b) {
    a = min;
    b = max;
    }
    if (a < 0 && b < 0) {
    counter = (long) ((a-b) / 2) + b;
    }
    else if (a > 0 && b > 0) {
    counter = (long) ((b-a) / 2) + a;
    }
    else
    counter = (long) (a+b) / 2;
    }
    public long val() {
    return counter; 
    }
    public void inc() {
    if (counter != a && counter != b) {
    counter++;
    }
    }
    public void dec() {
    if (counter != a && counter != b) {
    counter--;
    }
    }
    public boolean reached_min() {
    if (counter == a) {
    return true;
    }
    else return false; 
    }
    public boolean reached_max() {
    if (counter == b) {
    return true;
    }
    else return false;
    }
    public String toString() {
    return "["+a +","+counter+","+ b+"]";
    }
    public boolean equals(final AbsorbingCounter other) {
    return (((a==other.a) && (b==other.b)) || ((a==other.b) && (b==other.a))) && (counter==other.counter);
    }
  // UNIT TESTING:
  public static void main(final String[] args) {
    final AbsorbingCounter c1 = new AbsorbingCounter(0, 10);
    assert(c1.val() == 5);
    assert(! c1.reached_min());
    assert(! c1.reached_max());
    assert(c1.toString().equals("[0,5,10]"));
    assert(c1.equals(new AbsorbingCounter(0,10)));
    c1.inc(); assert(c1.val() == 6);
    assert(! c1.equals(new AbsorbingCounter(0,10)));
    c1.inc(); c1.inc(); c1.inc(); c1.inc(); assert(c1.val() == 10);
    assert(c1.reached_max());
    c1.inc(); assert(c1.val() == 10);
    c1.dec(); assert(c1.val() == 10);
    assert(c1.reached_max());
    final AbsorbingCounter c2 = new AbsorbingCounter(-10,10);
    assert(c2.val() == 0);
    assert(! c2.equals(c1));
    assert(c2.equals(new AbsorbingCounter(-10,10)));
    assert(c2.equals(new AbsorbingCounter(10,-10)));
    for (int i = 0; i < 10; ++i) c2.dec();
    assert(c2.val() == -10);
    assert(c2.reached_min());
    c2.dec(); assert(c2.val() == -10); assert(c2.reached_min());
    final AbsorbingCounter c3 = new AbsorbingCounter(0,10);
    assert(!c3.equals(c1));
    for (int i = 0; i < 5; ++i, c3.inc());
    assert(c3.equals(c1));
    final AbsorbingCounter c4 = new AbsorbingCounter(1,6);
    assert(c4.val() == 3);
    c4.dec(); c4.inc(); assert(c4.equals(new AbsorbingCounter(1,6)));
    final AbsorbingCounter c5 = new AbsorbingCounter(0,0);
    assert(c5.val() == 0);
    assert(c5.reached_min());
    assert(c5.reached_max());
    final AbsorbingCounter c6 = new AbsorbingCounter(Long.MIN_VALUE,Long.MAX_VALUE);
    assert(c6.val() == 0);
    assert(!c6.equals(c5));
    AbsorbingCounter cn = new AbsorbingCounter(Long.MAX_VALUE,Long.MAX_VALUE);
    assert(cn.val() == Long.MAX_VALUE);
    assert(cn.reached_min());
    assert(cn.reached_max());
    cn = new AbsorbingCounter(Long.MAX_VALUE-1, Long.MAX_VALUE);
    assert(cn.val() == Long.MAX_VALUE-1);
    assert(cn.reached_min());
    assert(!cn.reached_max());
    cn = new AbsorbingCounter(Long.MIN_VALUE,Long.MIN_VALUE);
    assert(cn.val() == Long.MIN_VALUE);
    assert(cn.reached_min());
    assert(cn.reached_max());
    cn = new AbsorbingCounter(Long.MIN_VALUE+1, Long.MIN_VALUE);
    assert(cn.val() == Long.MIN_VALUE+1);
    assert(!cn.reached_min());
    assert(cn.reached_max());
    assert(new AbsorbingCounter(10,30).val() == 20);
    assert(new AbsorbingCounter(10,31).val() == 20);
    assert(new AbsorbingCounter(11,30).val() == 20);
    assert(new AbsorbingCounter(9,30).val() == 19);
    assert(new AbsorbingCounter(10,29).val() == 19);
    assert(new AbsorbingCounter(10,32).val() == 21);
    assert(new AbsorbingCounter(12,30).val() == 21);
    assert(new AbsorbingCounter(-10,10).val() == 0);
    assert(new AbsorbingCounter(-10,11).val() == 0);
    assert(new AbsorbingCounter(-11,10).val() == 0);
    assert(new AbsorbingCounter(-9,10).val() == 0);
    assert(new AbsorbingCounter(-10,9).val() == 0);
    assert(new AbsorbingCounter(-10,12).val() == 1);
    assert(new AbsorbingCounter(-8,10).val() == 1);
    assert(new AbsorbingCounter(-12,10).val() == -1);
    assert(new AbsorbingCounter(-10,8).val() == -1);
    for (int x = -100; x <= 100; ++x)
      for (int y = -100; y <= 100; ++y) {
        final AbsorbingCounter c = new AbsorbingCounter(x, y);
        final double mean = ((double) x + y) / 2;
        final int mid = (int) mean;
        assert(c.val() == mid);
      }

      
      

    assert(Experiment.get_default_size() == Experiment.orig_default_size);
    Experiment.add_to_default(1);
    assert(Experiment.get_default_size() == Experiment.orig_default_size + 1);
    Experiment.add_to_default(-2);
    assert(Experiment.get_default_size() == Experiment.orig_default_size - 1);
    Experiment.add_to_default(- Experiment.orig_default_size);
    assert(Experiment.get_default_size() == 0);
    Experiment.add_to_default(Experiment.orig_default_size);
    assert(Experiment.get_default_size() == Experiment.orig_default_size);
    Experiment.add_to_default(Integer.MAX_VALUE);
    assert(Experiment.get_default_size() == Integer.MAX_VALUE);
  }
  }


class Stats {
  public long count_min_reached, count_max_reached;
  public double average;
  public String toString() {
    String res = "";
    res += "count min: " + count_min_reached + "\n";
    res += "count max: " + count_max_reached + "\n";
    res += "mean= " + average + "\n";
    return res;
  }
}


class Experiment {
	  public static final long default_seed = 0;
	  private static Random r = new Random(default_seed);
	  private static void set_seed(final long seed) {
	    r.setSeed(seed);
	  }
	  private static double random() { return r.nextDouble(); }

	  public static final int orig_default_size = 100;
	  private static int default_size = orig_default_size;
	  public static int get_default_size() {
	     return default_size;
	  }

	  public static int make_positive(final int x) {
	    int a = 0;
	    if (x > 0) a = x; else a = get_default_size();
	    return a;
	  }
	  public static int min(final int x, final long y) {
	    if (y < x) return (int) y; else return x;
	  }
	  public static void add_to_default(final int x) {
        int max = Integer.MAX_VALUE;
        if (default_size > 0 && x > 0) {
          if (default_size > (max - x)) {
              default_size = max;
             }
             }
        if ((default_size == max) || (x == max)) {
          default_size = max;
        }
        else {
         default_size = (default_size + x);
        }
        if (default_size < 0) default_size = 0;
        }
    
	  public static AbsorbingCounter[] create_experiment(final long min,
	    final long max, final int N) {
	    assert(N >= 1);
	    final AbsorbingCounter[] exp = new AbsorbingCounter[N];
	    for (int i = 0; i < N; ++i)
	      exp[i] = new AbsorbingCounter(min, max);
	    return exp;
	  }
	  public static void run_experiment(final AbsorbingCounter[] e,
	    final long T) {
	     double a = random(); 
	     for (long v = 0; v < T; v++) {
		   int i = (int) random();
		   if (a > 0.5) {
            e[i].dec();
		    }
		    else
		    e[i].inc();
		    }
    }
      public static Stats evaluate_experiment(final AbsorbingCounter[] e) {
	         Stats s1 = new Stats();
	         int M = e.length;
	         long[] counter = new long [M];
	         long sum = 0; 
	         s1.count_max_reached = 0;
	         s1.count_min_reached = 0; 
	         for (int i = 0; i < M; ++i) {
	          if (e[i].reached_max()) {
	          s1.count_max_reached++;
	          }
	          if (e[i].reached_min()) {
              s1.count_min_reached++;
              }
	          counter[i] = e[i].val();
              sum = sum + counter[i]; 
              }
              s1.average = (double) (sum / M);
              return s1;
	          }
      
      public static void main(final String[] args ) {
	    if (args.length < 3) {
	      System.err.print("ERROR[AbsorbingCounters]: ");
	      System.err.println("Arguments are\n min max T [N] [seed]");
	      System.exit(1);
	    }
	    final long min = Long.parseLong(args[0]);
	    final long max = Long.parseLong(args[1]);
	    final long T = Long.parseLong(args[2]);
	    final int N;
	    {final int temp = (args.length > 3) ? Integer.parseInt(args[3]) : 0;
	     N = make_positive(temp);}
	    final long seed = (args.length > 4) ?
	      Long.parseLong(args[4]) : default_seed;
	    set_seed(seed);
	    final AbsorbingCounter[] experiment = create_experiment(min, max, N);
	    run_experiment(experiment, T);
	    System.out.println("min=" + min + ", max=" + max + ", T=" + T +
	      ", N=" + experiment.length + ", seed=" + seed);
	    System.out.print(evaluate_experiment(experiment));
	  }
	}
	
	
	

