public class StandardSortingAlgorithm {
	public static final int BUBBLE_SORT = 0, SELECTION_SORT = 1, INSERTION_SORT = 2, HEAP_SORT = 3, QUICK_SORT = 4, MERGE_SORT = 5, SHELL_SORT = 6, NUM_OF_SORT_TYPES = 7;

	// 標準出力
	public static void print(int[] list, int min, int max){
		for (int i = min; i < max; i = i + 1){
			System.out.printf("%d, ", list[i]);
		}
		System.out.printf("%d\n", list[max]);
	}

	// ソート確認
	public static boolean sortConfirmation(int[] list, int min, int max){
		int tmp = list[0];

		for (int i = min; i <= max; i = i + 1){
			if (tmp > list[i]){
				return false;
			}
			tmp = list[i];
		}

		return true;
	}

	// ショートカット
	public static void shortcutSort(int[] list, int min, int max, int frag){
		switch (frag){
			case BUBBLE_SORT:
				bubbleSort(list, min, max);
				break;
			case SELECTION_SORT:
				selectionSort(list, min, max);
				break;
			case INSERTION_SORT:
				insertionSort(list, min, max);
				break;
			case HEAP_SORT:
				heapSort(list, min, max);
				break;
			case QUICK_SORT:
				quickSort(list, min, max);
				break;
			case MERGE_SORT:
				mergeSort(list, min, max);
				break;
			case SHELL_SORT:
				shellSort(list, min, max);
				break;
		}
	}

	// バブルソート
	public static void bubbleSort(int[] list, int min, int max){
		if (min >= max){
			return;
		}

		for (int i = max; i > min; i = i - 1){
			if (list[i] < list[i - 1]){
				swap(list, i, i - 1);
			}
		}

		bubbleSort(list, min + 1, max);
	}

	// 選択ソート
	public static void selectionSort(int[] list, int min, int max){
		if (min > max){
			return;
		}

		int n = min;
		for (int i = min; i <= max; i = i + 1){
			if (list[i] < list[n]){
				n = i;
			}
		}
		if (min < n){
			slide(list, min, n);
		}

		selectionSort(list, min + 1, max);
	}

	// 挿入ソート
	public static void insertionSort(int[] list, int min, int max){
		if (min > max){
			return;
		}

		boolean frag = true;
		for (int i = min; i > 0; i = i - 1){
			if (list[min] > list[i - 1]){
				slide(list, i, min);
				frag = false;
				break;
			}
		}
		if (frag){
			slide(list, 0, min);
		}

		insertionSort(list, min + 1, max);
	}

	// ヒープソート
	public static void heapSort(int[] list, int min, int max){
		if (max < 0){
			return;
		}

		makeHeap(list, min, max);
		swap(list, min, max);
		heapSort(list, min, max - 1);
	}

	// クイックソート
	public static void quickSort(int[] list, int min, int max){
		if (min >= max){
			return;
		}

		int p = (min + max) / 2;

		for (int i = min; i < p; i = i + 1){
			if (list[i] > list[p]){
				int tmp = list[i];
				for (int j = i; j < p; j = j + 1){
					list[j] = list[j + 1];
				}
				list[p] = tmp;
				p = p - 1;
				i = i - 1;
			}
		}
		for (int i = p + 1; i <= max; i = i + 1){
			if (list[p] > list[i]){
				slide(list, p, i);
				p = p + 1;
			}
		}

		quickSort(list, min, p - 1);
		quickSort(list, p + 1, max);
	}

	// マージソート
	public static void mergeSort(int[] list, int min, int max){
		int mid = (min + max) / 2;

		if (!((min < mid) && (mid < max))){
			if (min + 1 == max){
				if (list[min] > list[max]){
					swap(list, min, max);
				}
			}
			return;
		}

		mergeSort(list, min, mid);
		mergeSort(list, mid + 1, max);

		int p1 = min, p2 = mid + 1;
		for (int i = min; i <= max; i = i + 1){
			if (!((p2 > max) || (p1 >= p2))){
				if (list[p1] > list[p2]) {
					slide(list, p1, p2);
					p1 = p1 + 1;
					p2 = p2 + 1;
				} else {
					p1 = p1 + 1;
				}
			}
		}
	}

	// シェルソート
	public static void shellSort(int[] list, int min, int max){
		int interval = 1;

		while (interval < (max - min) + 1){
			interval = interval * 2;
		}

		for (interval = interval / 2 ;interval >= 1; interval = interval / 2){
			for (int i = 0; i < interval; i = i + 1){
				int n = 0;
				int[] table = new int[((max - min + 1) / interval) + 1];
				for (int j = min + i; j <= max; j = j + interval, n = n + 1){
					table[n] = list[j];
				}
				insertionSort(table, 0, n - 1);
				for (int j = min + i, k = 0; j <= max; j = j + interval, k = k + 1){
					list[j] = table[k];
				}
			}
		}
	}

	// スワップ
	private static void swap(int[] list, int min, int max){
		int tmp = list[min];
		list[min] = list[max];
		list[max] = tmp;
	}

	// スライド
	private static void slide(int[] list, int min, int max){
		int tmp = list[max];

		for (int i = max; i > min; i = i - 1){
			list[i] = list[i - 1];
		}
		list[min] = tmp;
	}

	// ヒープ作成
	private static void makeHeap(int[] list, int min, int max){
		for (int i = 0; i <= Math.sqrt(max - min); i = i + 1){
			for (int j = 1; j + min <= max + 1; j = j + 1){
				if (judgeRange(j * 2 + min, min, max)){
					judgeHeap(list, j + min - 1, j * 2 + min - 1, j * 2 + min);
				} else if (judgeRange(j * 2 + min - 1, min, max)){
					if (list[j + min - 1] < list[j * 2 + min - 1]){
						swap(list, j + min - 1, j * 2 + min - 1);
					}
				}
			}
		}
	}

	// ヒープ判定
	private static void judgeHeap(int[] list, int top, int bot1, int bot2){
		if ((list[top] > list[bot1]) && (list[top] > list[bot2])){
			return;
		}

		if (list[bot1] > list[bot2]){
			swap(list, bot1, top);
		} else {
			swap(list, bot2, top);
		}
	}

	// 範囲判定
	private static boolean judgeRange(int in, int min, int max){
		return ((min <= in) && (max >= in));
	}
}