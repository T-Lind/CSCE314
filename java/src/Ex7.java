//class A {
//    void go() { System.out.println("buy"); } // dynamic binding
//    static void to(A x) { System.out.println("kiwi"); } // static binding
//    static void to(B x) { System.out.println("fig");  } // static binding
//}
//class B extends A {
//    void go() { System.out.println("eat"); } // dynamic binding
//    static void to(A x) { System.out.println("apple"); } // static binding
//    static void to(B x) { System.out.println("pear");  } // static binding
//}
//class Ex7 {
//    public static void main(String[] args) {
//        A o1 = new A(); // o1's type (static binding) is A and
//        // o1 refers to an object of type A (dynamic binding)
//        A o2 = new B(); // o2's type (static binding) is A but
//        // o2 refers to an object of type B (dynamic binding)
//        B o3 = new B(); // o2's type (static binding) is A but
//        // o2 refers to an object of type B (dynamic binding)
//
//        o1.go();   // output1
//
//        o2.go();   // output2
//        o3.go();   // output3
//        o1.to(o1); // output4
//        o2.to(o2); // output5
//        o3.to(o3); // output6
//        o1.to(o3); // output7
//        o3.to(o1); // output8
//    }
//}
//
//
