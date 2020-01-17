# TeamLabオンラインプログラミング提出コード
Sample.javaにmain関数が入っている

GA.javaの諸変数
	
	POP_SIZE : 個体数
	GEN_MAX : 世代数
	OUTPUT_NUM : 1つの遺伝子に入る整数(つまり0～3が入っているようにする)
	GEN_GAP : 交叉させる個体の割合
	P_MUTATION : 突然変異の確率
	chrom : 個体数ぶんの遺伝子
	fitness : 個体それぞれの適合度

GA.javaの諸関数

	InitializeGA() : 遺伝的アルゴリズムの初期化
	Select() : 選択
	Mutation() : 突然変異
	ObjFunc() : 目的関数(これを最大化するような進化を行う)

Sample.javaで交叉を行う
