/*
 *Group 1, part B ICE project 2018
 */


// The pins that represent each individual vehicle lanes
int B = 1;
int D = 2;
int A = 3;
int C = 4;

// The pins that are connect to E-W lights
int EW_RedLed = 5;
int EW_GreenLed = 6;

// the pins that are connected to N-S lights
int NS_RedLed = 10;
int NS_GreenLed = 11;

/*
 *  Boolean values that represent when a lane is occupied,
 *  true is when the lane is occupied and false is when the lane is vacant.
 */
bool b_B;
bool b_D;
bool b_A;
bool b_C; 

void setup() {
  // Sets lanes as the pin inputs.
  pinMode(B, INPUT);
  pinMode(D, INPUT);
  pinMode(A, INPUT);
  pinMode(C, INPUT);

  // Sets all E-W LEDs and N-S LEDs as the program's outputs
  pinMode(EW_RedLed, OUTPUT);
  pinMode(EW_GreenLed, OUTPUT);

  pinMode(NS_RedLed, OUTPUT);
  pinMode(NS_GreenLed, OUTPUT);
}

// loop function for program

void loop() {
  inputRead();
  setEW_Leds();
  setNS_Leds();
}


 // this function controls the E-W signal.

void setEW_Leds() {
  if ((!b_C && ((!b_B && b_D) || (b_B && !b_D))) || ((!b_A && b_C) && (b_D || b_B)) || (!b_A && !b_C) || (b_B && b_D)) {
    digitalWrite(EW_GreenLed, HIGH);
    digitalWrite(EW_RedLed, LOW);
  } 
  
  else {
    digitalWrite(EW_GreenLed, LOW);
    digitalWrite(EW_RedLed, HIGH);
  }
}

 // This function controls the N-S signal.

void setNS_Leds() {
  if ((!b_B && !b_D && (b_C || b_A)) || (b_A && b_C &&(!b_B || !b_D))) {
    digitalWrite(NS_GreenLed, HIGH);
    digitalWrite(NS_RedLed, LOW);
  } 
  
  else {
    digitalWrite(NS_GreenLed, LOW);
    digitalWrite(NS_RedLed, HIGH); 
  }
}

  /* read function to read the input fromt the switches,
   * it then sets the boolean value true if the the input is 
   * HIGH, if not the else statement sets it boolean as false  
   * if the input is LOW.
   */
  
void inputRead() {               
  if (digitalRead(B) == HIGH) { 
    b_B = true;                   
  }                              
  
  else {
    b_B = false;
  }

  if (digitalRead(D) == HIGH) {
    b_D = true;
  } 
  
  else {
    b_D = false;
  }

  if (digitalRead(A) == HIGH) {
    b_A = true;
  } 
  
  else {
    b_A = false;
  }

  if (digitalRead(C) == HIGH) {
    b_C = true;  
  } 
  
  else {
    b_C = false;
  }
}
