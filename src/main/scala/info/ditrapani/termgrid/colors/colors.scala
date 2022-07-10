package info.ditrapani.termgrid.colors

extension (binaryDigits: String) def b: Int = Integer.parseInt(binaryDigits, 2)

// 6-bit colors from 4x4x4 color cube
val c000 = "000000".b
val c001 = "000001".b
val c002 = "000010".b
val c003 = "000011".b
val c010 = "000100".b
val c011 = "000101".b
val c012 = "000110".b
val c013 = "000111".b
val c020 = "001000".b
val c021 = "001001".b
val c022 = "001010".b
val c023 = "001011".b
val c030 = "001100".b
val c031 = "001101".b
val c032 = "001110".b
val c033 = "001111".b
val c100 = "010000".b
val c101 = "010001".b
val c102 = "010010".b
val c103 = "010011".b
val c110 = "010100".b
val c111 = "010101".b
val c112 = "010110".b
val c113 = "010111".b
val c120 = "011000".b
val c121 = "011001".b
val c122 = "011010".b
val c123 = "011011".b
val c130 = "011100".b
val c131 = "011101".b
val c132 = "011110".b
val c133 = "011111".b
val c200 = "100000".b
val c201 = "100001".b
val c202 = "100010".b
val c203 = "100011".b
val c210 = "100100".b
val c211 = "100101".b
val c212 = "100110".b
val c213 = "100111".b
val c220 = "101000".b
val c221 = "101001".b
val c222 = "101010".b
val c223 = "101011".b
val c230 = "101100".b
val c231 = "101101".b
val c232 = "101110".b
val c233 = "101111".b
val c300 = "110000".b
val c301 = "110001".b
val c302 = "110010".b
val c303 = "110011".b
val c310 = "110100".b
val c311 = "110101".b
val c312 = "110110".b
val c313 = "110111".b
val c320 = "111000".b
val c321 = "111001".b
val c322 = "111010".b
val c323 = "111011".b
val c330 = "111100".b
val c331 = "111101".b
val c332 = "111110".b
val c333 = "111111".b

val black = c000
val darkBlue = c001
val mediumBlue = c002
val blue = c003
val darkGreen = c010
val darkCyan = c011
val mediumGreen = c020
val mediumCyan = c022
val deepSkyBlue = c023
val green = c030
val cyan = c033
val darkRed = c100
val darkPurple = c102
val blueViolet = c103
val darkYellow = c110
val darkGrey = c111
val brightCyan = c133
val mediumRed = c200
val purple = c203
val mediumYellow = c220
val lightGrey = c222
val paleTurquoise = c233
val red = c300
val magenta = c303
val orange = c320
val yellow = c330
val white = c333

val colorMap6To8 = List(
  16,
  17,
  19,
  21,
  22,
  23,
  25,
  27,
  34,
  35,
  37,
  39,
  46,
  47,
  49,
  51,
  52,
  53,
  55,
  57,
  58,
  59,
  61,
  63,
  70,
  71,
  73,
  75,
  82,
  83,
  85,
  87,
  124,
  125,
  127,
  129,
  130,
  131,
  133,
  135,
  142,
  143,
  145,
  147,
  154,
  155,
  157,
  159,
  196,
  197,
  199,
  201,
  201,
  203,
  205,
  207,
  214,
  215,
  217,
  219,
  226,
  227,
  229,
  231,
)
