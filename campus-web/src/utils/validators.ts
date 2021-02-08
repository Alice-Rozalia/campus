import { ref } from 'vue'

export interface LoginData {
  username: string;
  password: string;
}

export const loginForm = ref<LoginData>({
  username: '',
  password: ''
})

export interface RegisterData {
  username: string;
  password: string;
  password2: string;
}

export const registerUser = ref<RegisterData>({
  username: '',
  password: '',
  password2: ''
})

interface Rules {
  username: ({
    required: boolean;
    message: string;
    trigger: string;
  } | {
    min: number;
    max: number;
    message: string;
    trigger: string;
  })[];
  password: ({
    required: boolean;
    message: string;
    trigger: string;
  } | {
    min: number;
    max: number;
    message: string;
    trigger: string;
  })[];
  password2: ({
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
}

const validatePassword2 = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请再次输入密码！'))
  } else if (value !== registerUser.value.password) {
    callback(new Error('两次输入的密码不一致！'))
  } else {
    callback()
  }
}

export const rules: Object = ref<Rules>({
  username: [
    { required: true, message: '请输入用户名！', trigger: 'blur' },
    { min: 2, max: 16, message: '长度在 2 到 16 个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码！', trigger: 'blur' },
    { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
  ],
  password2: [
    { required: true, message: '请确认密码！', trigger: 'blur' },
    { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' },
    { validator: validatePassword2, trigger: 'blur' }
  ]
})
