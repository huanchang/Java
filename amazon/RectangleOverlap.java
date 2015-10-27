public class RectangleOverlap {
    public static void main(String[] args) {
    
        // Overlap
        test(new Rectangle(0,2,4,0), new Rectangle(1,5,5,1));
        test(new Rectangle(0,2,4,0), new Rectangle(1,1,5,0));
        test(new Rectangle(0,2,4,0), new Rectangle(-1,1,1,-3));
        test(new Rectangle(0,2,4,0), new Rectangle(-1,3,1,1));
                
        // A in B
        test(new Rectangle(0,2,4,0), new Rectangle(-1,4,5,-1));
        
        // B in A
        test(new Rectangle(0,2,4,0), new Rectangle(1,1,2,0));
        
        // Adjacent
        test(new Rectangle(0,2,4,0), new Rectangle(4,-1,5,1));
        test(new Rectangle(0,2,4,0), new Rectangle(4,1,5,1));
        test(new Rectangle(0,2,4,0), new Rectangle(4,3,5,1));
    }
    
    public static void test(Rectangle a, Rectangle b) {
    
        System.out.printf("Node1: %s, Node2: %s, Result: ", a.toString(), b.toString());
        if (checkOverlap(a,b)) {
            System.out.printf("Yes.\n");
        } else {
            System.out.printf("No.\n");
        }
    }
    
    public static boolean checkOverlap(Rectangle a, Rectangle b) {
        // Assume that rectangle in represented by left-top-point and right-bottom-point
        return !(((a.bottomy>=b.topy)||(a.topy<=b.bottomy)) || ((a.bottomx<=b.topx)||(a.topx>=b.bottomx)) );
    }
    
    private static class Rectangle {   
        public int topx;
        public int topy;
        public int bottomx;
        public int bottomy;
        
        public Rectangle(int tx, int ty, int bx, int by) {
            this.topx = tx;
            this.topy = ty;
            this.bottomx = bx;
            this.bottomy = by;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("TOP(").append(topx).append(",").append(topy).append(")-");
            sb.append("BOTTOM(").append(bottomx).append(",").append(bottomy).append(")");
            return sb.toString();
        }
    }
}