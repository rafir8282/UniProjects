/*
 *Group 1, part C ICE project 2018
 */

// The pins that represent each individual lanes
int B = 2;
int D = 3;
int A = 4;
int C = 5;

int LDR_Pin = A0; // Assigns anolog pin A0 for the ldrPin
int LDR_Value; // declaring int for ldr values

// The pins that are connect to E-W lights
int EW_RedLed = 6;
int EW_GreenLed = 9;

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

  pinMode(LDR_Pin, INPUT);


  // Sets all E-W LEDs and N-S LEDs as the program's outputs
  pinMode(EW_RedLed, OUTPUT);
  pinMode(EW_GreenLed, OUTPUT);

  pinMode(NS_RedLed, OUTPUT);
  pinMode(NS_GreenLed, OUTPUT);

  Serial.begin(9600); // allows the LDR to communicate at 9600 bits per second for serial data transmission
}

// loop function for program

void loop() {
  LDR_Sensor();
  inputRead();
  setEW_Leds();
  setNS_Leds();
  Serial.println("LDR: " + String(LDR_Value) ); // to display ldr values in the serial monitor so you can note down limits for the mapping. (Q7)
  delay(500); // delays 500ms before program loops again.
}

 // this function controls the E-W signal.
 
void setEW_Leds() {
  if (((!b_A || !b_C) && (b_B || b_D)) || (b_B && b_D) || (!b_A && !b_C)) {
    analogWrite(EW_GreenLed, LDR_Value);
    digitalWrite(EW_RedLed, LOW);
            
  } 
  
  else {
    analogWrite(EW_RedLed, LDR_Value);
    digitalWrite(EW_GreenLed, LOW);
  }
}

// This function controls the N-S signal.

void setNS_Leds() {
  if ((!b_B && !b_D && (b_C || b_A)) || (b_A && b_C &&(!b_B || !b_D))) {
    digitalWrite(NS_RedLed, LOW);
    analogWrite(NS_GreenLed, LDR_Value);
    
  } 
  
  else {
    analogWrite(NS_RedLed, LDR_Value);
    digitalWrite(NS_GreenLed, LOW); 
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

// Function to control the LEDs based on the LDR value

void LDR_Sensor () {
  LDR_Value = analogRead(LDR_Pin);
  LDR_Value = map(LDR_Value, 0, 1000, 50, 255); //maps the ldr input reading of 0 to 1000 to the analog values between 50 and 255
  LDR_Value = constrain(LDR_Value, 50, 255); // constrains (limits) analog value of LEDs to between 50 and 255
}
