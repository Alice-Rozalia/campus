import { ref } from 'vue'

interface GoodsData {
  name: string;
  price: number | null;
  introduce: string;
  original: number | null;
  images: string;
  categoryId: number;
}

export const goodsForm = ref<GoodsData>({
  name: '',
  price: null,
  introduce: '',
  original: null,
  images: '',
  categoryId: 0
})

interface Rules {
  name: ({
    required: boolean;
    message: string;
    trigger: string;
  } | {
    min: number;
    max: number;
    message: string;
    trigger: string;
  })[];
  introduce: ({
    required: boolean;
    message: string;
    trigger: string;
  })[];
  price: ({
    required: boolean;
    message: string;
    trigger: string;
  } | {
    type: string;
    message: string;
    trigger: string;
  })[];
  categoryId: ({
    required: boolean;
    message: string;
    trigger: string;
  })[];
}

export const goodsRules: Object = ref<Rules>({
  name: [
    { required: true, message: '请输入商品名！', trigger: 'blur' },
    { min: 2, max: 60, message: '长度在 2 到 60 个字符', trigger: 'blur' }
  ],
  introduce: [
    { required: true, message: '请输入商品简介！', trigger: 'blur' }
  ],
  price: [
    { required: true, message: '请输入商品标价！', trigger: 'blur' },
    { type: 'number', message: '标价必须是数字！', trigger: 'blur' }
  ],
  categoryId: [
    { required: true, message: '请输入商品分类！', trigger: 'blur' }
  ]
})
