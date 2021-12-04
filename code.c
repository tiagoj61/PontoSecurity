#define ledPin 13
int data = 0;
void setup() {
  pinMode(ledPin, OUTPUT);
  digitalWrite(ledPin, LOW);
  Serial.print(78); 
  Serial.begin(9600); //default baud rate for bt 38400
}
void loop() {
  if(Serial.available() > 0){ // Checks whether data is comming from the serial port
    data = Serial.read(); // Reads the data from the serial port
    //Serial.print(data);
          if (data == '0') {
            digitalWrite(ledPin, LOW); // Turn LED OFF
            Serial.println("LED: OFF"); 
            Serial.print(78); 
            }
              else if (data == '1') {
                Serial.print(78); 
                digitalWrite(ledPin, HIGH);
                Serial.println("LED: ON");
               Serial.write('0');
              } 
  }
}