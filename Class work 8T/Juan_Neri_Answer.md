# Classwork 1T
## Main Method

    public static void main (String[] args) {

          Scanner input = new Scanner(System.in);                // ┒
          System.out.println("Enter a sentence.");               // │ C1
          String[] words = input.nextLine().split(" ");          // │
          int count = 0;                                         // ┚

          for (int i = 0; i < words.length; i++) {               // │ C2             ┒
              boolean unique = true;                             // │ C3             │ #n
              for (int j = 0; j < words.length; j++) {           // │ C4    ┒        │
                  if (words[i].equals(words[j]) && i != j) {     // ┒       │ #n     │
                      unique = false;                            // │ C5    │        │
                      break;                                     // ┚       ┚        │
                  }                                                                  │
              }                                                                      │
              if (unique) {count++;}                             // │ C6             ┚
          }
          System.out.println(count);                             // │ C7
    }

### Answer
 *T(n) = C1 + n[ C2 + C3 + n( C4 + C5 ) + C6 ] + C7*<br/>
 **O(N^2)**

# Classwork 3R
## getAt Method

    public int getAt(int index) {
       return items[index];     // │ C1
    }

### Answer
 *T(n) = C1*<br/>
 **O(1)**

## checkAndGrow Method

    private void checkAndGrow () {

        if (size < items.length) { return; }                // │ C1
        int[] newItems = new int[items.length * 2];         // │ C2

        for (int i = 0; i < items.length; i++) {            // │ C3     ┒ #n ( this code only executes if
            newItems[i] = items[i];                         // │ C4     ┚         items.length == size )
        }

        items = newItems;                                   // │ C5
    }

### Answer
 *T(n) = C1 + C2 + n( C3 + C4 ) + C5 or T(n) = C1*<br/>
 In either case, the function has asymptotic upper bound given by<br/>
 **O(n)**

## shiftRight Method

    private void shiftRight (int index) {

        for (int i = size; i > index; i--) {            // │ C1   ┒ #n
            items[i] = items[i-1];                      // │ C2   ┚
        }

    }

### Answer
 *T(n) = n( C1 + C2 )*<br/>
 **O(n)**

## insertAt Method

    public void insertAt(int toAdd, int index) {

        size++;                     // │ C0
        checkAndGrow();             // │ C1 + C2 + n( C3 + C4 ) + C5
        shiftRight(index);          // │ n( C6 + C7 )
        items[index] = toAdd;       // │ C8
    }

### Answer
 *T(n) = C0 + C1 + C2 + n( C3 + C4 ) + C5 + n( C6 + C7 ) + C8*<br/>
 **O(n)**
