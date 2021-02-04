import { withRouter } from 'next/router'
import Link from 'next/link'

const Someone = ({ router }) => {
  return (
    <>
      <div>{router.query.name}在此</div>
      <Link href="/"><a className="home">返回首页</a></Link>

      <style jsx>
        {`
            div{color: #00a4ff;font-size:28px;}
            .home{font-size:18px;}
          `}
      </style>
    </>
  )
}

export default withRouter(Someone)
