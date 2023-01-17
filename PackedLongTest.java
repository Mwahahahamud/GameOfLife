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

import static com.google.common.truth.Truth.assertThat;

import com.google.common.truth.Truth;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertThrows;
@RunWith(JUnit4.class)
public class PackedLongTest {

  @Test
  public void set_onlyChangesAffectedPosition() {
    // ARRANGE
//    long initial = 0xF00000000000000FL;

    PackedLong initial = new PackedLong(0xF00000000000000FL);

    // ACT
    initial.set(4, true);
//    long updated = PackedLong.set(initial, 4, true);

    // ASSERT
    assertThat(initial.getStoredLong()).isEqualTo(0xF00000000000001FL);
  }

  @Test
  public void clear_onlyChangesAffectedPosition() {
    // ARRANGE
//    long initial = 0xF00000000000001FL;
    PackedLong initial = new PackedLong(0xF00000000000001FL);

    // ACT
//    long updated = PackedLong.set(initial, 4, false);
    initial.set(4, false);

    // ASSERT
    assertThat(initial.getStoredLong()).isEqualTo(0xF00000000000000FL);
  }


  @Test
  public void setLowestBitTo0AtPosition63(){
    PackedLong initial = new PackedLong(0xFFFFFFFFFFFFFFFFL);
    initial.set(63, false);
    assertThat(initial.getStoredLong()).isEqualTo(0x7FFFFFFFFFFFFFFFL);
  }

  @Test
  public void getStoredLongReturnsRightValue(){
    PackedLong initial = new PackedLong(0x0123456789FEDCBAL);
    Long storedValue = initial.getStoredLong();
    assertThat(storedValue).isEqualTo(0x0123456789FEDCBAL);
  }

  @Test
  public void initialisesTo0(){
    PackedLong initial = new PackedLong();
    assertThat(initial.getStoredLong()).isEqualTo(0L);
  }


  @Test
  public void testPublicStaticMethods(){
    Long initial = 0L;
    PackedLong.set(initial, 0, true);
    PackedLong.get(initial, 0);
    PackedLong.set(initial, 0, false);
  }
@Test
  public void checkSizeThrowsError() throws UnsupportedOperationException{
    PackedLong initial = new PackedLong(0L);

//    try{
//      initial.size();
//    }
//    catch (UnsupportedOperationException e){
//
//    }
//    assertThat(initial.size()).isEqualTo(0);
    assertThrows(UnsupportedOperationException.class, () -> initial.size());
  }

  @Test
  public void toStringWorks(){
    PackedLong initial = new PackedLong();
    assertThat(initial.toString()).isEqualTo("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
  }
//  0xFFFFFFFFFFFFFFFFL
//  0x8FFFFFFFFFFFFFFFL
  @Test
  public void set_setsHighestBit_atPosition63() {
    // ARRANGE
//    long initial = 0x0000000000000000L;
    PackedLong initial = new PackedLong(0x0000000000000000L);

    // ACT
//    long updated = PackedLong.set(initial, 63, true);
    initial.set(63, true);

    // ASSERT
    assertThat(initial.getStoredLong()).isEqualTo(0x8000000000000000L);
  }

  @Test
  public void get_getsHighestBit_whenPosition63IsSet() {
    // ARRANGE
//    long initial = 0x8000000000000000L;
    PackedLong initial = new PackedLong(0x8000000000000000L);

    // ACT
//    boolean value = PackedLong.get(initial, 63);
//    initial.get(63);

    // ASSERT
    assertThat(initial.get(63)).isTrue();
  }

  @Test
  public void get_getsHighestBit_whenPosition63IsClear() {
    // ARRANGE
//    long initial = 0x7000000000000000L;
    PackedLong initial = new PackedLong(0x7000000000000000L);

    // ACT
//    boolean value = PackedLong.get(initial, 63);

    // ASSERT
    assertThat(initial.get(63)).isFalse();
  }
}
