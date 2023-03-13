package com.welie.blessed


import org.junit.Test
import org.junit.Assert.*
import java.nio.ByteOrder
import java.nio.ByteOrder.BIG_ENDIAN
import java.nio.ByteOrder.LITTLE_ENDIAN
import java.util.*

class ByteArrayExtenstionsTest {

    @Test
    fun test_fromHexString() {
        assertArrayEquals(byteArrayOf(0.toByte(), 0.toByte()), byteArrayOf("0000"))
        assertArrayEquals(byteArrayOf(0xFF.toByte(), 0x80.toByte()), byteArrayOf("FF80"))
        assertArrayEquals(byteArrayOf(0x80.toByte(), 0xFF.toByte()), byteArrayOf("80FF"))
        assertArrayEquals(byteArrayOf(0x00.toByte(), 0xFF.toByte()), byteArrayOf("00FF"))
        assertArrayEquals(byteArrayOf(0x00.toByte(), 0xFF.toByte(), 0x80.toByte(), 0x81.toByte(), 0xA0.toByte(), 0xEF.toByte()), byteArrayOf("00FF8081A0EF"))
    }

    @Test
    fun test_asHexString() {
        assertEquals("61626300", byteArrayOf("61626300").asHexString())
    }

    @Test
    fun test_formattedHexString() {
        assertEquals("61:62:63:00", byteArrayOf("61626300").formatHexBytes(":"))
    }

    @Test
    fun test_getString() {
        assertEquals( "abc", byteArrayOf("616263").getString(0u))
        assertEquals( "abc", byteArrayOf("61626320").getString(0u))
        assertEquals( "abc", byteArrayOf("6162630061").getString(0u))
        assertEquals( "c", byteArrayOf("61626300").getString(2u))
    }

    @Test
    fun test_uint8() {
        assertEquals(7u, byteArrayOf("07").getUInt8(0u))
        assertEquals(15u, byteArrayOf("0F").getUInt8(0u))
        assertEquals(26u, byteArrayOf("1A").getUInt8(0u))
        assertEquals(255u, byteArrayOf("FF").getUInt8(0u))
    }

    @Test
    fun test_int8() {
        assertEquals(7, byteArrayOf("07").getInt8(0u))
        assertEquals(15, byteArrayOf("0F").getInt8(0u))
        assertEquals(127, byteArrayOf("7F").getInt8(0u))
        assertEquals(-128, byteArrayOf("80").getInt8(0u))
        assertEquals(-1, byteArrayOf("FF").getInt8(0u))
    }

    // 16-bit variants

    @Test
    fun test_uint16_le() {
        assertEquals(1799u, byteArrayOf("0707").getUInt16(0u, LITTLE_ENDIAN))
        assertEquals(59143u, byteArrayOf("07E7").getUInt16(0u, LITTLE_ENDIAN))
        assertEquals(2023u, byteArrayOf("E707").getUInt16(0u, LITTLE_ENDIAN))
        assertEquals(59367u, byteArrayOf("E7E7").getUInt16(0u, LITTLE_ENDIAN))
        assertEquals(65535u, byteArrayOf("FFFF").getUInt16(0u, LITTLE_ENDIAN))
    }

    @Test
    fun test_uint16_be() {
        assertEquals(1799u, byteArrayOf("0707").getUInt16(0u, BIG_ENDIAN))
        assertEquals(2023u, byteArrayOf("07E7").getUInt16(0u, BIG_ENDIAN))
        assertEquals(59143u, byteArrayOf("E707").getUInt16(0u, BIG_ENDIAN))
        assertEquals(59367u, byteArrayOf("E7E7").getUInt16(0u, BIG_ENDIAN))
        assertEquals(65535u, byteArrayOf("FFFF").getUInt16(0u, BIG_ENDIAN))
    }

    @Test
    fun test_int16_le() {
        assertEquals(1799, byteArrayOf("0707").getInt16(0u, LITTLE_ENDIAN))
        assertEquals(-6393, byteArrayOf("07E7").getInt16(0u, LITTLE_ENDIAN))
        assertEquals(2023, byteArrayOf("E707").getInt16(0u, LITTLE_ENDIAN))
        assertEquals(-6169, byteArrayOf("E7E7").getInt16(0u, LITTLE_ENDIAN))
        assertEquals(-1, byteArrayOf("FFFF").getInt16(0u, LITTLE_ENDIAN))
    }

    @Test
    fun test_int16_be() {
        assertEquals(1799, byteArrayOf("0707").getInt16(0u, BIG_ENDIAN))
        assertEquals(2023, byteArrayOf("07E7").getInt16(0u, BIG_ENDIAN))
        assertEquals(-6393, byteArrayOf("E707").getInt16(0u, BIG_ENDIAN))
        assertEquals(-6169, byteArrayOf("E7E7").getInt16(0u, BIG_ENDIAN))
        assertEquals(-1, byteArrayOf("FFFF").getInt16(0u, BIG_ENDIAN))
    }

    // 24-bit variants

    @Test
    fun test_uint24_le() {
        assertEquals(460551u, byteArrayOf("070707").getUInt24(0u, LITTLE_ENDIAN))
        assertEquals(15197959u, byteArrayOf("07E7E7").getUInt24(0u, LITTLE_ENDIAN))
        assertEquals(15140839u, byteArrayOf("E707E7").getUInt24(0u, LITTLE_ENDIAN))
        assertEquals(15198183u, byteArrayOf("E7E7E7").getUInt24(0u, LITTLE_ENDIAN))
        assertEquals(16777215u, byteArrayOf("FFFFFF").getUInt24(0u, LITTLE_ENDIAN))
    }

    @Test
    fun test_uint24_be() {
        assertEquals(460551u, byteArrayOf("070707").getUInt24(0u, BIG_ENDIAN))
        assertEquals(518119u, byteArrayOf("07E7E7").getUInt24(0u, BIG_ENDIAN))
        assertEquals(15140839u, byteArrayOf("E707E7").getUInt24(0u, BIG_ENDIAN))
        assertEquals(15198183u, byteArrayOf("E7E7E7").getUInt24(0u, BIG_ENDIAN))
        assertEquals(16777215u, byteArrayOf("FFFFFF").getUInt24(0u, BIG_ENDIAN))
    }

    @Test
    fun test_int24_le() {
        assertEquals(460551, byteArrayOf("070707").getInt24(0u, LITTLE_ENDIAN))
        assertEquals( -1579257, byteArrayOf("07E7E7").getInt24(0u, LITTLE_ENDIAN))
        assertEquals(-1636377, byteArrayOf("E707E7").getInt24(0u, LITTLE_ENDIAN))
        assertEquals(-1579033, byteArrayOf("E7E7E7").getInt24(0u, LITTLE_ENDIAN))
        assertEquals(-1, byteArrayOf("FFFFFF").getInt24(0u, LITTLE_ENDIAN))
    }

    @Test
    fun test_int24_be() {
        assertEquals(460551, byteArrayOf("070707").getInt24(0u, BIG_ENDIAN))
        assertEquals(518119, byteArrayOf("07E7E7").getInt24(0u, BIG_ENDIAN))
        assertEquals(-1636377, byteArrayOf("E707E7").getInt24(0u, BIG_ENDIAN))
        assertEquals(-1579033, byteArrayOf("E7E7E7").getInt24(0u, BIG_ENDIAN))
        assertEquals(-1, byteArrayOf("FFFFFF").getInt24(0u, BIG_ENDIAN))
    }

    // 32-bit variants

    @Test
    fun test_uint32_le() {
        assertEquals(117901063u, byteArrayOf("07070707").getUInt32(0u, LITTLE_ENDIAN))
        assertEquals(3876054791u, byteArrayOf("07E707E7").getUInt32(0u, LITTLE_ENDIAN))
        assertEquals(132581351u, byteArrayOf("E707E707").getUInt32(0u, LITTLE_ENDIAN))
        assertEquals(3890735079u, byteArrayOf("E7E7E7E7").getUInt32(0u, LITTLE_ENDIAN))
        assertEquals(4294967295u, byteArrayOf("FFFFFFFF").getUInt32(0u, LITTLE_ENDIAN))
    }

    @Test
    fun test_uint32_be() {
        assertEquals(117901063u, byteArrayOf("07070707").getUInt32(0u, BIG_ENDIAN))
        assertEquals(132581351u, byteArrayOf("07E707E7").getUInt32(0u, BIG_ENDIAN))
        assertEquals(3876054791u, byteArrayOf("E707E707").getUInt32(0u, BIG_ENDIAN))
        assertEquals(3890735079u, byteArrayOf("E7E7E7E7").getUInt32(0u, BIG_ENDIAN))
        assertEquals(4294967295u, byteArrayOf("FFFFFFFF").getUInt32(0u, BIG_ENDIAN))
    }

    @Test
    fun test_int32_le() {
        assertEquals(117901063, byteArrayOf("07070707").getInt32(0u, LITTLE_ENDIAN))
        assertEquals(-418912505, byteArrayOf("07E707E7").getInt32(0u, LITTLE_ENDIAN))
        assertEquals(132581351, byteArrayOf("E707E707").getInt32(0u, LITTLE_ENDIAN))
        assertEquals(-404232217, byteArrayOf("E7E7E7E7").getInt32(0u, LITTLE_ENDIAN))
        assertEquals(-1, byteArrayOf("FFFFFFFF").getInt32(0u, LITTLE_ENDIAN))
    }

    @Test
    fun test_int32_be() {
        assertEquals(117901063, byteArrayOf("07070707").getInt32(0u, BIG_ENDIAN))
        assertEquals(132581351, byteArrayOf("07E707E7").getInt32(0u, BIG_ENDIAN))
        assertEquals(-418912505, byteArrayOf("E707E707").getInt32(0u, BIG_ENDIAN))
        assertEquals(-404232217, byteArrayOf("E7E7E7E7").getInt32(0u, BIG_ENDIAN))
        assertEquals(-1, byteArrayOf("FFFFFFFF").getInt32(0u, BIG_ENDIAN))
    }

    // 48-bit variants

    @Test
    fun test_uint48_le() {
        assertEquals(7726764066567u, byteArrayOf("070707070707").getUInt48(0u, LITTLE_ENDIAN))
        assertEquals(254021126842119u, byteArrayOf("07E707E707E7").getUInt48(0u, LITTLE_ENDIAN))
        assertEquals(8688851421159u, byteArrayOf("E707E707E707").getUInt48(0u, LITTLE_ENDIAN))
        assertEquals(254983214196711u, byteArrayOf("E7E7E7E7E7E7").getUInt48(0u, LITTLE_ENDIAN))
        assertEquals(281474976710655u, byteArrayOf("FFFFFFFFFFFF").getUInt48(0u, LITTLE_ENDIAN))
    }

    @Test
    fun test_uint48_be() {
        assertEquals(7726764066567u, byteArrayOf("070707070707").getUInt48(0u, BIG_ENDIAN))
        assertEquals(8688851421159u, byteArrayOf("07E707E707E7").getUInt48(0u, BIG_ENDIAN))
        assertEquals(254021126842119u, byteArrayOf("E707E707E707").getUInt48(0u, BIG_ENDIAN))
        assertEquals(254983214196711u, byteArrayOf("E7E7E7E7E7E7").getUInt48(0u, BIG_ENDIAN))
        assertEquals(281474976710655u, byteArrayOf("FFFFFFFFFFFF").getUInt48(0u, BIG_ENDIAN))
    }

    @Test
    fun test_int48_le() {
        assertEquals(7726764066567, byteArrayOf("070707070707").getInt48(0u, LITTLE_ENDIAN))
        assertEquals(-27453849868537, byteArrayOf("07E707E707E7").getInt48(0u, LITTLE_ENDIAN))
        assertEquals(8688851421159, byteArrayOf("E707E707E707").getInt48(0u, LITTLE_ENDIAN))
        assertEquals(-26491762513945, byteArrayOf("E7E7E7E7E7E7").getInt48(0u, LITTLE_ENDIAN))
        assertEquals(-1, byteArrayOf("FFFFFFFFFFFF").getInt48(0u, LITTLE_ENDIAN))
    }

    @Test
    fun test_int48_be() {
        assertEquals(7726764066567, byteArrayOf("070707070707").getInt48(0u, BIG_ENDIAN))
        assertEquals(8688851421159, byteArrayOf("07E707E707E7").getInt48(0u, BIG_ENDIAN))
        assertEquals(-27453849868537, byteArrayOf("E707E707E707").getInt48(0u, BIG_ENDIAN))
        assertEquals(-26491762513945, byteArrayOf("E7E7E7E7E7E7").getInt48(0u, BIG_ENDIAN))
        assertEquals(-1, byteArrayOf("FFFFFFFFFFFF").getInt48(0u, BIG_ENDIAN))
    }

    // 64-bit variants

    @Test
    fun test_uint64_le() {
        assertEquals(506381209866536711u, byteArrayOf("0707070707070707").geUInt64(0u, LITTLE_ENDIAN))
        assertEquals(16647528568725169927u, byteArrayOf("07E707E707E707E7").geUInt64(0u, LITTLE_ENDIAN))
        assertEquals(569432566737078247u, byteArrayOf("E707E707E707E707").geUInt64(0u, LITTLE_ENDIAN))
        assertEquals(16710579925595711463u, byteArrayOf("E7E7E7E7E7E7E7E7").geUInt64(0u, LITTLE_ENDIAN))
        assertEquals(18446744073709551615u, byteArrayOf("FFFFFFFFFFFFFFFF").geUInt64(0u, LITTLE_ENDIAN))
    }

    @Test
    fun test_uint64_be() {
        assertEquals(506381209866536711u, byteArrayOf("0707070707070707").geUInt64(0u, BIG_ENDIAN))
        assertEquals(569432566737078247u, byteArrayOf("07E707E707E707E7").geUInt64(0u, BIG_ENDIAN))
        assertEquals(16647528568725169927u, byteArrayOf("E707E707E707E707").geUInt64(0u, BIG_ENDIAN))
        assertEquals(16710579925595711463u, byteArrayOf("E7E7E7E7E7E7E7E7").geUInt64(0u, BIG_ENDIAN))
        assertEquals(18446744073709551615u, byteArrayOf("FFFFFFFFFFFFFFFF").geUInt64(0u, BIG_ENDIAN))
    }

    @Test
    fun test_sint64_le() {
        assertEquals(506381209866536711, byteArrayOf("0707070707070707").geInt64(0u, LITTLE_ENDIAN))
        assertEquals(-1799215504984381689, byteArrayOf("07E707E707E707E7").geInt64(0u, LITTLE_ENDIAN))
        assertEquals(569432566737078247, byteArrayOf("E707E707E707E707").geInt64(0u, LITTLE_ENDIAN))
        assertEquals(-1736164148113840153, byteArrayOf("E7E7E7E7E7E7E7E7").geInt64(0u, LITTLE_ENDIAN))
        assertEquals(-1, byteArrayOf("FFFFFFFFFFFFFFFF").geInt64(0u, LITTLE_ENDIAN))
    }

    @Test
    fun test_sint64_be() {
        assertEquals(506381209866536711, byteArrayOf("0707070707070707").geInt64(0u, BIG_ENDIAN))
        assertEquals(569432566737078247, byteArrayOf("07E707E707E707E7").geInt64(0u, BIG_ENDIAN))
        assertEquals(-1799215504984381689, byteArrayOf("E707E707E707E707").geInt64(0u, BIG_ENDIAN))
        assertEquals(-1736164148113840153, byteArrayOf("E7E7E7E7E7E7E7E7").geInt64(0u, BIG_ENDIAN))
        assertEquals(-1, byteArrayOf("FFFFFFFFFFFFFFFF").geInt64(0u, BIG_ENDIAN))
    }

    // Floats

    @Test
    fun test_sfloat_le() {
        assertEquals(513.0, byteArrayOf("0102").getSFloat(order = LITTLE_ENDIAN), 0.1)
        assertEquals(-51.1, byteArrayOf("01FE").getSFloat(order = LITTLE_ENDIAN), 0.1)
        assertEquals(767.0, byteArrayOf("FF02").getSFloat(order = LITTLE_ENDIAN), 0.1)
        assertEquals(-193.6, byteArrayOf("70F8").getSFloat(order = LITTLE_ENDIAN), 0.1)
    }

    @Test
    fun test_sfloat_be() {
        assertEquals(513.0, byteArrayOf("0201").getSFloat(order = BIG_ENDIAN), 0.1)
        assertEquals(-51.1, byteArrayOf("FE01").getSFloat(order = BIG_ENDIAN), 0.1)
        assertEquals(767.0, byteArrayOf("02FF").getSFloat(order = BIG_ENDIAN), 0.1)
        assertEquals(-193.6, byteArrayOf("F870").getSFloat(order = BIG_ENDIAN), 0.1)
        assertEquals(11.2, byteArrayOf("F070").getSFloat(order = BIG_ENDIAN), 0.1)
    }

    @Test
    fun test_float_le() {
        assertEquals(1.97121E9, byteArrayOf("01020304").getFloat(order = LITTLE_ENDIAN), 0.001)
        assertEquals(26.1633, byteArrayOf("01FE03FC").getFloat(order = LITTLE_ENDIAN), 0.001)
        assertEquals(-1.95841E9, byteArrayOf("FF02FD04").getFloat(order = LITTLE_ENDIAN), 0.001)
        assertEquals(36.4, byteArrayOf("6C0100FF").getFloat(order = LITTLE_ENDIAN), 0.1)
    }

    @Test
    fun test_float_be() {
        assertEquals(1.97121E9, byteArrayOf("04030201").getFloat(order = BIG_ENDIAN), 0.001)
        assertEquals(26.1633, byteArrayOf("FC03FE01").getFloat(order = BIG_ENDIAN), 0.001)
        assertEquals(-1.95841E9, byteArrayOf("04FD02FF").getFloat(order = BIG_ENDIAN), 0.001)
        assertEquals(36.4, byteArrayOf("FF00016C").getFloat(order = BIG_ENDIAN), 0.1)
    }

    // Dates

    @Test
    fun test_getDateTime() {
        val calendar = GregorianCalendar()
        calendar.time = byteArrayOf("E40701020a1530").getDateTime()

        assertEquals(2020, calendar.get(GregorianCalendar.YEAR))
        assertEquals(1, calendar.get(GregorianCalendar.MONTH) + 1);
        assertEquals(2, calendar.get(GregorianCalendar.DAY_OF_MONTH));
        assertEquals(10, calendar.get(GregorianCalendar.HOUR_OF_DAY));
        assertEquals(21, calendar.get(GregorianCalendar.MINUTE));
        assertEquals(48, calendar.get(GregorianCalendar.SECOND));
    }

    // Create 16 bit byte arrays

    @Test
    fun test_create_uint16_le() {
        assertEquals(1799u, byteArrayOf(1799u, 2u, LITTLE_ENDIAN).getUInt16(order = LITTLE_ENDIAN))
        assertEquals(59143u, byteArrayOf(59143u, 2u, LITTLE_ENDIAN).getUInt16(order = LITTLE_ENDIAN))
        assertEquals(59367u, byteArrayOf(59367u, 2u, LITTLE_ENDIAN).getUInt16(order = LITTLE_ENDIAN))
        assertEquals(65535u, byteArrayOf(65535u, 2u, LITTLE_ENDIAN).getUInt16(order = LITTLE_ENDIAN))
    }

    @Test
    fun test_create_uint16_be() {
        assertEquals(1799u, byteArrayOf(1799u, 2u, BIG_ENDIAN).getUInt16(order = BIG_ENDIAN))
        assertEquals(59143u, byteArrayOf(59143u, 2u, BIG_ENDIAN).getUInt16(order = BIG_ENDIAN))
        assertEquals(59367u, byteArrayOf(59367u, 2u, BIG_ENDIAN).getUInt16(order = BIG_ENDIAN))
        assertEquals(65535u, byteArrayOf(65535u, 2u, BIG_ENDIAN).getUInt16(order = BIG_ENDIAN))
    }

    @Test
    fun test_create_int16_le() {
        assertEquals(1799, byteArrayOf(1799, 2u, LITTLE_ENDIAN).getInt16(order = LITTLE_ENDIAN))
        assertEquals(-6393, byteArrayOf(-6393, 2u, LITTLE_ENDIAN).getInt16(order = LITTLE_ENDIAN))
        assertEquals(-6169, byteArrayOf(-6169, 2u, LITTLE_ENDIAN).getInt16(order = LITTLE_ENDIAN))
        assertEquals(-1, byteArrayOf(-1, 2u, LITTLE_ENDIAN).getInt16(order = LITTLE_ENDIAN))
    }

    @Test
    fun test_uint16_ext() {
        assertArrayEquals(1000u.asUInt16(LITTLE_ENDIAN), byteArrayOf(1000u, 2u, LITTLE_ENDIAN))
        assertArrayEquals(1000u.asUInt16(BIG_ENDIAN), byteArrayOf(1000u, 2u, BIG_ENDIAN))
    }

    @Test
    fun test_int16_ext() {
        assertArrayEquals(1000.asInt16(LITTLE_ENDIAN), byteArrayOf(1000, 2u, LITTLE_ENDIAN))
        assertArrayEquals(1000.asInt16(BIG_ENDIAN), byteArrayOf(1000, 2u, BIG_ENDIAN))
        assertArrayEquals((-1000).asInt16(LITTLE_ENDIAN), byteArrayOf(-1000, 2u, LITTLE_ENDIAN))
        assertArrayEquals((-1000).asInt16(BIG_ENDIAN), byteArrayOf(-1000, 2u, BIG_ENDIAN))
    }

    @Test
    fun test_create_int16_be() {
        assertEquals(1799, byteArrayOf(1799, 2u, BIG_ENDIAN).getInt16(order = BIG_ENDIAN))
        assertEquals(-6393, byteArrayOf(-6393, 2u, BIG_ENDIAN).getInt16(order = BIG_ENDIAN))
        assertEquals(-6169, byteArrayOf(-6169, 2u, BIG_ENDIAN).getInt16(order = BIG_ENDIAN))
        assertEquals(-1, byteArrayOf(-1, 2u, BIG_ENDIAN).getInt16(order = BIG_ENDIAN))
    }

    // TODO - 24 bit & 32 bit & 48 bit & 64 bit
    // TODO - float & sfloat


}