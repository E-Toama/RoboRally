package AI.logic.utilities.tablebases;

public class MapArrays {

    public static int[][] dizzyRight = {
            {30, 20, 15, 14, 10000, 10000, 15, 14, 13, 12, 11, 11, 8},
            {20, 20, 14, 13, 13, 18, 17, 16, 18, 11, 10, 10, 1000},
            {20, 20, 13, 12, 13, 12, 11, 10, 9, 8, 7, 9, 1000},
            {20, 20, 15, 9, 11, 10, 9, 8, 12, 11, 6, 1, 0},
            {20, 20, 19, 12, 11, 10, 9, 7, 7, 6, 5, 3, 2},
            {20, 20, 19, 12, 8, 7, 6, 5, 4, 3, 2, 4, 3},
            {20, 20, 12, 11, 11, 12, 11, 10, 7, 6, 4, 5, 4},
            {20, 20, 11, 12, 11, 10, 9, 8, 7, 6, 5, 6, 5},
            {20, 20, 12, 11, 10, 9, 8, 7, 6, 4, 6, 7, 6},
            {20, 20, 13, 12, 11, 10, 9, 8, 7, 5, 7, 10000, 7}};

    public static int[][] dizzyUp = {
        {20, 20, 16, 15, 10000, 10000, 15, 14, 13, 12, 8, 8, 10},
        {20, 20, 15, 14, 13, 18, 17, 16, 18, 11, 14, 14, 1000},
        {20, 20, 14, 13, 13, 12, 11, 10, 9, 8, 6, 7, 1000},
        {20, 20, 16, 10, 11, 10, 9, 8, 12, 11, 3, 11, 0},
        {20, 20, 14, 13, 11, 10, 9, 7, 7, 6, 4, 7, 1},
        {20, 20, 14, 13, 8, 7, 6, 5, 4, 3, 5, 2, 2},
        {20, 20, 13, 12, 11, 12, 11, 10, 7, 6, 6, 8, 3},
        {20, 20, 12, 13, 11, 10, 9, 8, 7, 6, 7, 5, 4},
        {20, 20, 13, 12, 10, 9, 8, 7, 6, 4, 7, 6, 5},
        {20, 20, 14, 13, 11, 10, 9, 8, 7, 5, 9, 10000, 6}};

    public static int[][] dizzyLeft = {
        {20, 22, 17, 18, 10000, 10000, 15, 14, 13, 12, 7, 8, 10},
        {20, 22, 16, 15, 13, 18, 17, 16, 18, 11, 13, 11, 1000},
        {20, 22, 15, 14, 13, 12, 11, 10, 9, 8, 5, 9, 1000},
        {20, 22, 17, 11, 11, 10, 9, 8, 12, 11, 4, 5, 0},
        {20, 22, 15, 14, 11, 10, 9, 7, 7, 6, 5, 6, 2},
        {20, 22, 15, 14, 8, 7, 6, 5, 4, 3, 4, 7, 3},
        {20, 22, 14, 13, 11, 12, 11, 10, 7, 6, 7, 7, 4},
        {20, 22, 13, 14, 11, 10, 9, 8, 7, 6, 7, 5, 5},
        {20, 22, 14, 13, 10, 9, 8, 7, 6, 4, 8, 5, 6},
        {20, 22, 15, 14, 11, 10, 9, 8, 7, 5, 7, 10000, 7}};

    public static int[][] dizzyDown = {
        {20, 21, 16, 15, 10000, 10000, 15, 14, 13, 12, 6, 8, 10},
        {20, 21, 15, 14, 13, 18, 17, 16, 18, 11, 5, 15, 1000},
        {20, 21, 14, 13, 13, 12, 11, 10, 9, 8, 4, 7, 1000},
        {20, 21, 11, 10, 11, 10, 9, 8, 12, 11, 3, 9, 0},
        {20, 21, 14, 13, 11, 10, 9, 7, 7, 6, 6, 7, 3},
        {20, 21, 14, 13, 8, 7, 6, 5, 4, 3, 7, 2, 4},
        {20, 21, 13, 12, 11, 12, 11, 10, 7, 6, 8, 8, 5},
        {20, 21, 12, 13, 11, 10, 9, 8, 7, 6, 9, 5, 6},
        {20, 21, 13, 12, 10, 9, 8, 7, 6, 4, 8, 6, 7},
        {20, 21, 14, 13, 11, 10, 9, 8, 7, 5, 10, 10000, 8}}
    ;

    public static int[][] ExtraCrispyDownCP1 = {{21, 20, 1000, 19, 1000, 17, 16, 14, 12, 11, 10, 13, 7}, {30, 20, 17, 17, 1000, 13, 11, 1000, 10, 9, 8, 14, 6}, {30, 30, 19, 18, 1000, 30, 1000, 13, 12, 1000, 0, 15, 3}, {20, 30, 20, 19, 1000, 1000, 1000, 14, 13, 1000, 1000, 6, 6}, {30, 20, 30, 23, 21, 20, 20, 10, 9, 6, 2, 7, 1000}, {30, 20, 30, 26, 25, 21, 18, 17, 14, 11, 3, 8, 1000}, {20, 30, 23, 26, 1000, 1000, 1000, 17, 16, 1000, 1000, 1000, 1000}, {30, 30, 24, 23, 1000, 1000, 1000, 18, 17, 1000, 20, 10, 1000}, {30, 20, 17, 16, 15, 14, 1000, 12, 12, 1000, 11, 11, 1000}, {30, 30, 25, 30, 30, 30, 18, 17, 16, 15, 17, 9, 1000}};
    public static int[][] ExtraCrispyDownCP2 = {{15, 14, 11, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30}, {12, 13, 10, 12, 30, 30, 31, 31, 31, 31, 31, 31, 30}, {11, 12, 9, 11, 1000, 30, 15, 32, 32, 15, 32, 31, 30}, {10, 11, 8, 10, 1000, 1000, 15, 33, 32, 15, 32, 31, 30}, {9, 10, 7, 7, 6, 3, 34, 33, 34, 33, 32, 31, 30}, {8, 9, 6, 6, 5, 2, 30, 33, 34, 33, 32, 31, 30}, {7, 8, 5, 4, 7, 1, 15, 33, 33, 15, 32, 31, 30}, {6, 4, 4, 3, 8, 0, 15, 17, 20, 15, 32, 31, 30}, {9, 9, 7, 6, 10, 10, 11, 14, 17, 16, 31, 31, 30}, {10, 9, 6, 7, 15, 14, 15, 30, 30, 30, 30, 30, 30}};
    public static int[][] ExtraCrispyDownCP3 ={{30, 30, 25, 30, 30, 30, 18, 17, 16, 15, 17, 9, 1000}, {30, 20, 17, 16, 15, 14, 1000, 12, 12, 1000, 11, 11, 1000}, {30, 30, 24, 23, 1000, 1000, 1000, 18, 17, 1000, 20, 10, 1000}, {20, 30, 23, 26, 1000, 1000, 1000, 17, 16, 1000, 1000, 1000, 1000}, {30, 20, 30, 26, 25, 21, 18, 17, 14, 11, 3, 8, 1000}, {30, 20, 30, 23, 21, 20, 20, 10, 9, 6, 2, 7, 1000}, {20, 30, 20, 19, 1000, 1000, 1000, 14, 13, 1000, 1000, 6, 6}, {30, 30, 19, 18, 1000, 30, 1000, 13, 12, 1000, 0, 15, 3}, {30, 20, 17, 17, 1000, 13, 11, 1000, 10, 9, 8, 14, 6}, {21, 20, 1000, 19, 1000, 17, 16, 14, 12, 11, 10, 13, 7}};
    public static int[][] ExtraCrispyDownCP4 = {{10, 12, 1000, 7, 1000, 10, 29, 30, 30, 30, 30, 30, 30}, {9, 11, 7, 6, 15, 15, 30, 31, 31, 31, 31, 31, 30}, {6, 5, 4, 3, 4, 0, 31, 32, 32, 15, 32, 31, 30}, {7, 6, 5, 4, 15, 15, 33, 33, 33, 15, 32, 31, 30}, {8, 7, 6, 6, 5, 2, 34, 33, 34, 33, 32, 31, 30}, {9, 8, 7, 7, 6, 3, 34, 33, 34, 33, 32, 31, 30}, {10, 9, 8, 22, 1000, 1000, 15, 33, 33, 15, 32, 31, 30}, {11, 12, 9, 23, 1000, 32, 15, 32, 32, 15, 32, 31, 30}, {12, 13, 10, 24, 31, 31, 31, 31, 31, 31, 31, 31, 30}, {13, 14, 11, 25, 30, 30, 30, 30, 30, 30, 30, 30, 30}};
    public static int[][] ExtraCrispyUpCP1 = {{21, 20, 1000, 19, 1000, 17, 16, 14, 12, 11, 10, 13, 7}, {30, 20, 17, 17, 1000, 13, 11, 1000, 10, 9, 8, 14, 6}, {30, 30, 19, 18, 1000, 30, 1000, 13, 12, 1000, 0, 15, 3}, {20, 30, 20, 19, 1000, 1000, 1000, 14, 13, 1000, 1000, 6, 6}, {30, 20, 30, 23, 21, 20, 20, 10, 9, 6, 2, 7, 1000}, {30, 20, 30, 26, 25, 21, 18, 17, 14, 11, 3, 8, 1000}, {20, 30, 23, 26, 1000, 1000, 1000, 17, 16, 1000, 1000, 1000, 1000}, {30, 30, 24, 23, 1000, 1000, 1000, 18, 17, 1000, 20, 10, 1000}, {30, 20, 17, 16, 15, 14, 1000, 12, 12, 1000, 11, 11, 1000}, {30, 30, 25, 30, 30, 30, 18, 17, 16, 15, 17, 9, 1000}};
    public static int[][] ExtraCrispyUpCP2 = {{15, 14, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30}, {14, 13, 20, 20, 30, 30, 31, 31, 31, 31, 31, 31, 30}, {13, 12, 11, 20, 1000, 30, 15, 32, 32, 15, 32, 31, 30}, {12, 11, 10, 20, 1000, 1000, 15, 33, 32, 15, 32, 31, 30}, {11, 10, 9, 8, 7, 6, 34, 33, 34, 33, 32, 31, 30}, {10, 9, 8, 7, 6, 7, 30, 33, 34, 33, 32, 31, 30}, {9, 8, 7, 7, 7, 15, 15, 33, 33, 15, 32, 31, 30}, {5, 4, 4, 3, 8, 0, 15, 17, 20, 15, 32, 31, 30}, {7, 8, 5, 5, 10, 10, 11, 14, 17, 16, 31, 31, 30}, {8, 9, 6, 5, 14, 11, 14, 30, 30, 30, 30, 30, 30}};
    public static int[][] ExtraCrispyUpCP3 ={{30, 30, 24, 30, 30, 30, 15, 14, 13, 12, 15, 7, 1000}, {30, 20, 16, 15, 14, 13, 18, 11, 10, 9, 8, 16, 1000}, {30, 30, 19, 23, 1000, 1000, 1000, 18, 17, 1000, 20, 15, 1000}, {20, 30, 19, 24, 1000, 1000, 1000, 17, 16, 1000, 20, 20, 1000}, {30, 20, 30, 25, 24, 23, 17, 16, 14, 5, 4, 7, 1000}, {30, 20, 30, 21, 20, 19, 10, 9, 8, 7, 3, 6, 1000}, {20, 30, 20, 19, 1000, 1000, 1000, 14, 13, 1000, 20, 6, 5}, {30, 30, 19, 18, 1000, 30, 1000, 13, 12, 1000, 0, 5, 4}, {30, 20, 16, 15, 1000, 13, 11, 1000, 9, 8, 7, 6, 5}, {20, 19, 1000, 17, 1000, 15, 14, 13, 12, 11, 10, 13, 6}};
    public static int[][] ExtraCrispyUpCP4 = {{10, 12, 1000, 7, 1000, 10, 29, 30, 30, 30, 30, 30, 30}, {9, 11, 7, 6, 15, 15, 30, 31, 31, 31, 31, 31, 30}, {6, 5, 4, 3, 4, 0, 31, 32, 32, 15, 32, 31, 30}, {7, 6, 5, 4, 15, 15, 33, 33, 33, 15, 32, 31, 30}, {8, 7, 6, 6, 5, 2, 34, 33, 34, 33, 32, 31, 30}, {9, 8, 7, 7, 6, 3, 34, 33, 34, 33, 32, 31, 30}, {10, 9, 8, 22, 1000, 1000, 15, 33, 33, 15, 32, 31, 30}, {11, 12, 9, 23, 1000, 32, 15, 32, 32, 15, 32, 31, 30}, {12, 13, 10, 24, 31, 31, 31, 31, 31, 31, 31, 31, 30}, {13, 14, 11, 25, 30, 30, 30, 30, 30, 30, 30, 30, 30}};
    public static int[][] ExtraCrispyLeftCP1 = {{20, 19, 1000, 19, 30, 18, 17, 20, 19, 12, 11, 15, 6}, {30, 20, 18, 17, 30, 15, 20, 1000, 11, 10, 9, 14, 5}, {30, 30, 19, 19, 1000, 20, 1000, 15, 13, 1000, 0, 1, 2}, {20, 30, 20, 20, 1000, 1000, 1000, 16, 14, 1000, 1000, 1000, 5}, {30, 20, 30, 22, 24, 24, 23, 12, 11, 9, 3, 4, 1000}, {30, 20, 30, 26, 25, 24, 23, 19, 18, 6, 4, 5, 1000}, {20, 30, 23, 26, 1000, 1000, 1000, 17, 16, 1000, 21, 21, 1000}, {30, 30, 24, 25, 1000, 1000, 1000, 18, 17, 1000, 1000, 15, 1000}, {30, 20, 18, 17, 16, 15, 18, 13, 12, 11, 10, 16, 1000}, {30, 30, 24, 30, 30, 30, 16, 15, 14, 13, 16, 7, 1000}};
    public static int[][] ExtraCrispyLeftCP2 = {{15, 14, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30}, {14, 13, 12, 20, 30, 30, 31, 31, 31, 31, 31, 31, 30}, {13, 12, 11, 20, 1000, 30, 15, 32, 32, 15, 32, 31, 30}, {12, 11, 10, 20, 1000, 1000, 15, 33, 32, 15, 32, 31, 30}, {11, 10, 9, 8, 7, 6, 34, 33, 34, 33, 32, 31, 30}, {10, 9, 8, 7, 6, 7, 30, 33, 34, 33, 32, 31, 30}, {9, 8, 7, 7, 15, 15, 15, 33, 33, 15, 32, 31, 30}, {7, 6, 5, 4, 5, 0, 15, 17, 20, 15, 32, 31, 30}, {7, 8, 5, 5, 10, 10, 11, 14, 17, 16, 31, 31, 30}, {9, 10, 6, 6, 14, 11, 14, 30, 30, 30, 30, 30, 30}};
    public static int[][] ExtraCrispyLeftCP3 = {{30, 30, 24, 30, 30, 30, 16, 15, 14, 13, 16, 7, 1000}, {30, 20, 18, 17, 16, 15, 18, 13, 12, 11, 10, 16, 1000}, {30, 30, 24, 25, 1000, 1000, 1000, 18, 17, 1000, 1000, 15, 1000}, {20, 30, 23, 26, 1000, 1000, 1000, 17, 16, 1000, 21, 21, 1000}, {30, 20, 30, 26, 25, 24, 23, 19, 18, 6, 4, 5, 1000}, {30, 20, 30, 22, 24, 24, 23, 12, 11, 9, 3, 4, 1000}, {20, 30, 20, 20, 1000, 1000, 1000, 16, 14, 1000, 1000, 1000, 5}, {30, 30, 19, 19, 1000, 20, 1000, 15, 13, 1000, 0, 1, 2}, {30, 20, 18, 17, 30, 15, 20, 1000, 11, 10, 9, 14, 5}, {20, 19, 1000, 19, 30, 18, 17, 20, 19, 12, 11, 15, 6}};
    public static int[][] ExtraCrispyLeftCP4 = {{11, 10, 1000, 8, 1000, 11, 29, 30, 30, 30, 30, 30, 30}, {10, 9, 8, 7, 15, 30, 30, 31, 31, 31, 31, 31, 30}, {7, 6, 5, 4, 5, 0, 31, 32, 32, 15, 32, 31, 30}, {10, 9, 8, 7, 15, 15, 33, 33, 33, 15, 32, 31, 30}, {11, 10, 9, 7, 6, 3, 34, 33, 34, 33, 32, 31, 30}, {12, 11, 10, 8, 7, 4, 34, 33, 34, 33, 32, 31, 30}, {13, 12, 11, 22, 1000, 1000, 15, 33, 33, 15, 32, 31, 30}, {14, 13, 12, 23, 1000, 32, 15, 32, 32, 15, 32, 31, 30}, {15, 14, 13, 24, 31, 31, 31, 31, 31, 31, 31, 31, 30}, {16, 15, 1000, 25, 30, 30, 30, 30, 30, 30, 30, 30, 30}};
    public static int[][] ExtraCrispyRightCP1 = {{20, 19, 1000, 17, 1000, 15, 14, 13, 12, 11, 10, 13, 6}, {30, 20, 16, 15, 1000, 13, 11, 1000, 9, 8, 7, 6, 5}, {30, 30, 19, 18, 1000, 30, 1000, 13, 12, 1000, 0, 5, 4}, {20, 30, 20, 19, 1000, 1000, 1000, 14, 13, 1000, 20, 6, 5}, {30, 20, 30, 21, 20, 19, 10, 9, 8, 7, 3, 6, 1000}, {30, 20, 30, 25, 24, 23, 17, 16, 14, 5, 4, 7, 1000}, {20, 30, 19, 24, 1000, 1000, 1000, 17, 16, 1000, 20, 20, 1000}, {30, 30, 19, 23, 1000, 1000, 1000, 18, 17, 1000, 20, 15, 1000}, {30, 20, 16, 15, 14, 13, 18, 11, 10, 9, 8, 16, 1000}, {30, 30, 24, 30, 30, 30, 15, 14, 13, 12, 15, 7, 1000}};
    public static int[][] ExtraCrispyRightCP2 = {{14, 13, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30}, {13, 12, 11, 7, 30, 30, 31, 31, 31, 31, 31, 31, 30}, {12, 11, 10, 20, 1000, 30, 15, 32, 32, 15, 32, 31, 30}, {11, 10, 9, 20, 1000, 1000, 15, 33, 32, 15, 32, 31, 30}, {10, 9, 8, 20, 5, 4, 34, 33, 34, 33, 32, 31, 30}, {9, 8, 7, 5, 4, 3, 30, 33, 34, 33, 32, 31, 30}, {8, 7, 6, 5, 1000, 1000, 15, 33, 33, 5, 32, 31, 30}, {5, 4, 3, 2, 1, 0, 15, 19, 20, 15, 32, 31, 30}, {8, 7, 6, 5, 10, 11, 12, 13, 14, 31, 31, 31, 30}, {9, 8, 7, 6, 12, 11, 14, 30, 30, 30, 30, 30, 30}};
    public static int[][] ExtraCrispyRightCP3 = {{30, 30, 24, 30, 30, 30, 15, 14, 13, 12, 15, 7, 1000}, {30, 20, 16, 15, 14, 13, 18, 11, 10, 9, 8, 16, 1000}, {30, 30, 19, 23, 1000, 1000, 1000, 18, 17, 1000, 20, 15, 1000}, {20, 30, 19, 24, 1000, 1000, 1000, 17, 16, 1000, 20, 20, 1000}, {30, 20, 30, 25, 24, 23, 17, 16, 14, 5, 4, 7, 1000}, {30, 20, 30, 21, 20, 19, 10, 9, 8, 7, 3, 6, 1000}, {20, 30, 20, 19, 1000, 1000, 1000, 14, 13, 1000, 20, 6, 5}, {30, 30, 19, 18, 1000, 30, 1000, 13, 12, 1000, 0, 5, 4}, {30, 20, 16, 15, 1000, 13, 11, 1000, 9, 8, 7, 6, 5}, {20, 19, 1000, 17, 1000, 15, 14, 13, 12, 11, 10, 13, 6}};
    public static int[][] ExtraCrispyRightCP4 = {{9, 8, 1000, 6, 1000, 11, 29, 30, 30, 30, 30, 30, 30}, {8, 7, 6, 5, 15, 30, 30, 31, 31, 31, 31, 31, 30}, {5, 4, 3, 2, 5, 0, 31, 32, 32, 15, 32, 31, 30}, {8, 7, 6, 5, 15, 15, 33, 33, 33, 15, 32, 31, 30}, {9, 8, 6, 5, 4, 3, 34, 33, 34, 33, 32, 31, 30}, {10, 9, 7, 6, 5, 4, 34, 33, 34, 33, 32, 31, 30}, {11, 10, 9, 22, 1000, 1000, 15, 33, 33, 15, 32, 31, 30}, {12, 11, 10, 23, 1000, 32, 15, 32, 32, 15, 32, 31, 30}, {13, 12, 11, 24, 31, 31, 31, 31, 31, 31, 31, 31, 30}, {14, 13, 1000, 25, 30, 30, 30, 30, 30, 30, 30, 30, 30}};


    private  int[][] dizzySpiral = {
            {13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 3, 3, 3},
            {13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 2, 2},
            {13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1},
            {13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0},
            {13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1},
            {13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 2, 2},
            {13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 3, 3, 3},
            {13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 4, 4, 4, 4},
            {13, 12, 11, 10, 9, 8, 7, 6, 5, 5, 5, 5, 5, 5},
            {13, 12, 11, 10, 9, 8, 7, 6, 6, 6, 6, 6, 6, 6}
    };

}
