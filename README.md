# StandardSortingAlgorithm
標準的なソーティングアルゴリズムを実装したライブラリ的なものを作成しました。
ここでいう標準的というのは基本情報技術者試験のシラバスに記載されている7つのソーティングアルゴリズムのことを指します。

基本的にすべてのメソッドはint型配列と対象となるポインタ2つ(始点と終点)を受け取り、それをもとに動作します。
配列の内容を標準出力するprintや、ソートされているかどうかを判別するsortConfirmation、各種ソーティングアルゴリズムを呼び出すshortcutSortというメソッドなどを用意しています。
当然今挙げたメソッドや各種ソーティングアルゴリズムはpublicでアクセス可能です。
一方でソーティングに用いたメソッドはprivateしてあります。
public、privateにかかわらず何をするメソッドなのかは明記してありますので参照や再利用していただいてもかまいません。

メソッドのリストと簡単な説明を下に用意しました。

[public]

public static void print(int[] list, int min, int max); // 標準出力

public static boolean sortConfirmation(int[] list, int min, int max); // ソート確認

public static void shortcutSort(int[] list, int min, int max, int frag); // ショートカット

public static void bubbleSort(int[] list, int min, int max); // バブルソート

public static void selectionSort(int[] list, int min, int max); // 選択ソート

public static void insertionSort(int[] list, int min, int max); // 挿入ソート

public static void heapSort(int[] list, int min, int max); // ヒープソート

public static void quickSort(int[] list, int min, int max); // クイックソート

public static void mergeSort(int[] list, int min, int max); // マージソート

public static void shellSort(int[] list, int min, int max); // シェルソート

[private]

private static void swap(int[] list, int min, int max); // スワップ

private static void slide(int[] list, int min, int max); // スライド

private static void makeHeap(int[] list, int min, int max); // ヒープ作成

private static void judgeHeap(int[] list, int top, int bot1, int bot2); // ヒープ判定

private static boolean judgeRange(int in, int min, int max); // 範囲判定

# Update Log
20230516 アップロード
