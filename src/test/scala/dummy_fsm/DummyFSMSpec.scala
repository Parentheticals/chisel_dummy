package dummy_fsm

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class DummyFSMSpec extends FreeSpec with ChiselScalatestTester {
  "Dummy FSM test" in {
    test(new Dummy_FSM(4, 6.U)) { dut =>
        dut.io.out.ready.poke(false.B)
        dut.io.in.valid.poke(false.B)
        dut.clock.step(3)
        // Start
        dut.io.out.ready.poke(true.B)
        dut.io.in.valid.poke(true.B)
        dut.io.in.bits.poke(2.U)
        // wait to finish
        for (i <- 0 until 7) {
          dut.clock.step(1)
          dut.io.out.valid.expect(false.B)
          // println("bits are: " + dut.io.out.bits.peek() + "\n")
        }
        dut.clock.step(1)
        // println("bits are: " + dut.io.out.bits.peek() + "\n")
        dut.io.out.bits.expect(3.U)
        dut.io.out.valid.expect(true.B)
        dut.clock.step(1)
        // println("bits are: " + dut.io.out.bits.peek() + "\n")
        dut.io.out.valid.expect(false.B)
    }
  }

  "Dummy nonFSM test" in {
    test(new Dummy_nonFSM(4, 6.U)) { dut =>
        dut.io.out.ready.poke(false.B)
        dut.io.in.valid.poke(false.B)
        dut.clock.step(3)
        // Start
        dut.io.out.ready.poke(true.B)
        dut.io.in.valid.poke(true.B)
        dut.io.in.bits.poke(2.U)
        // wait to finish
        for (i <- 0 until 7) {
          dut.clock.step(1)
          dut.io.out.valid.expect(false.B)
        }
        dut.clock.step(1)
        dut.io.out.bits.expect(3.U)
        dut.io.out.valid.expect(true.B)
        dut.clock.step(1)
        dut.io.out.bits.expect(0.U)
        dut.io.out.valid.expect(false.B)
    }
  }
}