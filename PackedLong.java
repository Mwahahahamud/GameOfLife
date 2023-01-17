/*
 * Copyright 2023 David Berry <dgb37@cam.ac.uk>, Joe Isaacs <josi2@cam.ac.uk>, Andrew Rice <acr31@cam.ac.uk>, M. Haji
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.cam.mh2169.game_of_life;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

class PackedLong extends AbstractList<Boolean> {

  long storedLong;
  /**
   * Unpack the nth bit from the packed number and return it
   *
   * @param packed the packed number
   * @param position the position of the bit we are interested in from 0 to 63 inclusive
   * @return the value of the position'th bit
   */
  public static boolean get(long packed, int position) {
    return ((packed >>> position) & 1) == 1;
  }

  /**
   * Set the nth bit in the packed number to the value given and return the new packed number
   *
   * @param packed the packed number
   * @param position the position of the bit of interest
   * @param value the value to set the bit to
   * @return the new packed number
   */
  public static long set(long packed, int position, boolean value) {
    if (value) {
      packed |= 1L << position;
    } else {
      packed &= ~(1L << position);
    }
    return packed;
  }

  public long getStoredLong(){
    return this.storedLong;
  }

  public void set(int position, boolean value){
    long mask = 1L << position;
    if (value == true){
      this.storedLong = this.storedLong | mask;
    }
    else{
      this.storedLong = ~((~this.storedLong) | mask);
    }

  }

  // No instances
  public PackedLong() {
    this.storedLong = 0L;
  }

  public PackedLong(long value){
    this.storedLong = value;
  }
  @Override
  public int size() throws UnsupportedOperationException{ // Should never be used; throw an error if so.
    throw new UnsupportedOperationException();
  }

  @Override
  public Boolean get(int index) {
    long mask = 1L << index;
    long result = (this.storedLong & mask) >>> index;
    return (result == 1L);
  }
  @Override
  public String toString(){
    List<String> all = new ArrayList<>();
    for (int i = 0; i < 64; i++) {
      all.add(String.valueOf(this.get(i) ? 1 : 0));
    }
    return String.join(",", all);
  }

}
