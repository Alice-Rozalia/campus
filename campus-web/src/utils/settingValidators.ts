import { ref } from 'vue'

interface SettingData {
  address: string;
  phone: string;
  code: string;
}


export const setForm = ref<SettingData>({
  address: '',
  phone: '',
  code: ''
})

interface Rules {
  address: ({
    required: boolean;
    message: string;
    trigger: string;
  } | {
    min: number;
    max: number;
    message: string;
    trigger: string;
  })[];
  phone: ({
    required: boolean;
    message: string;
    trigger: string;
  } | {
    min: number;
    max: number;
    message: string;
    trigger: string;
  } | {
    validator: (rule: Rules, value: string, callback: any) => void;
    trigger: string;
  })[];
  code: ({
    required: boolean;
    message: string;
    trigger: string;
  } | {
    min: number;
    max: number;
    message: string;
    trigger: string;
  })[];
}

// 验证手机号的规则
const checkPhone = (rule: any, value: any, cb: any) => {
  const regPhone = /^(0|86|17951)?(13[0-9]|15[012356789]|17[3678]|18[0-9]|14[57]|19[189])[0-9]{8}$/

  if (regPhone.test(value)) {
    return cb()
  }

  cb(new Error('请输入合法的手机号！'))
}

export const setRules: Object = ref<Rules>({
  address: [
    { required: true, message: '请输入地址！', trigger: 'blur' },
    { min: 2, max: 60, message: '长度在 2 到 60 个字符！', trigger: 'blur' }
  ],
  phone: [
    { required: true, message: '请输入手机号！', trigger: 'blur' },
    { validator: checkPhone, trigger: 'blur' }
  ],
  code: [
    { required: true, message: '请输入短信验证码！', trigger: 'blur' },
    { min: 6, max: 6, message: '短信验证码为 6 位数字！', trigger: 'blur' }
  ]
})
